# 레드-블랙 트리 (Red-Black Tree) 정리

레드-블랙 트리는 **두 가지 색(RED/BLACK)** 을 이용해 **높이를 O(log n)** 으로 유지하는 **균형 이진 탐색 트리**입니다.  
탐색/삽입/삭제가 모두 **최악 O(log n)** 입니다.

---

## 핵심 불변식(반드시 암기)

1. **각 노드는 Red 또는 Black**
2. **루트는 Black**
3. **모든 NIL(리프 센티넬)은 Black**
4. **Red의 자식은 모두 Black** (부모-자식 **연속 Red 금지**)
5. **어떤 노드에서 NIL까지 모든 경로의 Black 개수(흑높이)가 동일**

> 4,5번이 높이를 O(log n)으로 제한합니다.

---

## 회전(Rotation) 직관 & ASCII

**좌회전(leftRotate(x))**: `x`의 **오른쪽 자식**이 **위로**, `x`는 **왼쪽으로** 내려감  
**우회전(rightRotate(x))**: `x`의 **왼쪽 자식**이 **위로**, `x`는 **오른쪽으로** 내려감

### 좌회전 예 (leftRotate(p))
회전 전 회전 후
p z
/ \ /
A z ─────▶ p C
/ \ /
B C A B

### 우회전 예 (rightRotate(p))

회전 전 회전 후
p z
/ \ /
z C ─────▶ A p
/ \ /
A B B C


BST의 **중위순서(정렬)** 는 **항상 보존**됩니다.

---

## 삼촌/바깥·안쪽 용어

- `z`: 새로 삽입된 노드(초기 **Red**)
- `p = parent(z)`, `g = parent(p)` (부모, 조부모)
- **삼촌 `u` = g의 다른 자식 = (p가 g.left면 g.right, 그 반대면 g.left)**
- **바깥(outside)** = 일직선(zig-zig): **LL**(`z==p.left && p==g.left`), **RR**(`z==p.right && p==g.right`)
- **안쪽(inside)** = 지그재그(zig-zag): **LR**(`z==p.right && p==g.left`), **RL**(`z==p.left && p==g.right`)

---

## 삽입 Fix-Up 요약(면접 답변용 표)

전제: `p`가 **Red**일 때(= 위반) 처리.  
`u`의 색과 `z`-`p`-`g` 배치에 따라 아래 중 하나:

### A) **삼촌 `u`가 Red** (재색칠 케이스)
- **동작**: `p`와 `u`를 **Black**, `g`를 **Red**
- **계속**: `z ← g` 로 올려서 while 반복
- **효과**: 아래 레벨의 위반 제거, 위로 올려 해결 시도(회전 없음)

### B) **삼촌 `u`가 Black** & **바깥(LL/RR)** (일직선)
- **LL**: **`rightRotate(g)`** 후 **`p=Black, g=Red`**
- **RR**: **`leftRotate(g)`** 후 **`p=Black, g=Red`**
- **끝**: 이 케이스는 **회전 1번 + 재색칠**로 종료

### C) **삼촌 `u`가 Black** & **안쪽(LR/RL)** (지그재그)
- **LR**: **`leftRotate(p)`** 로 **LL**로 변환 → **B-LL** 처리
- **RL**: **`rightRotate(p)`** 로 **RR**로 변환 → **B-RR** 처리
- **끝**: 결국 **부모 1회 회전 + 조부모 1회 회전 + 재색칠**

> 루프 종료 후 **`root`는 반드시 Black** 으로 (`root.setRed(false)`).

---

## 사용자가 정리한 예시 (보완·정정 포함)

### 1) **u 가 Black & z 가 바깥(LL/RR)**

#### (1) LL
초기(색상은 예시일 뿐, `g`는 Red/Black일 수 있음):
g(•)
/   \
p(R) u(B)
/
z(R)
**동작**: `rightRotate(g)` → **재색칠**: `p=Black`, `g=Red`  
결과(서브트리 생략):
p(B)
/
z g(R)

u(B)

#### (2) RR (거울)
초기:

g(•)
/
u(B) p(R)

z(R)

**동작**: `leftRotate(g)` → **재색칠**: `p=Black`, `g=Red`  
결과(서브트리 생략):
p(B)
/   \

g(R) z
/
u(B)

> 위 두 케이스는 **조부모 기준 회전 1번**으로 끝납니다.

---

### 2) **u 가 Black & z 가 안쪽(LR/RL)**

#### (1) LR

g(•)
/
p(R)

z(R)

1) **`leftRotate(p)`** 로 **LL**로 변환
2) **`rightRotate(g)` + 재색칠**(새 꼭대기 Black, 옛 g Red)

#### (2) RL (거울)
g(•)

p(R)
/
z(R)

1) **`rightRotate(p)`** 로 **RR**로 변환
2) **`leftRotate(g)` + 재색칠**

---

### 3) **u 가 Red** (재색칠만)

g(•)
/
p(R) u(R)

z(R)


- **`p,u → Black`, `g → Red`**, 그 다음 **`z ← g`** 로 올리고 while 반복
- **회전 없음** (위로 올려 다음 단계에서 처리)

---

## 삽입 Fix-Up 의사코드(간단)

```java
void fixInsert(Node z) {
  while (z.parent.isRed()) {
    Node p = z.parent, g = p.parent;
    if (p == g.left) {
      Node u = g.right;
      if (u.isRed()) {                 // A: uncle red
        p.setRed(false); u.setRed(false);
        g.setRed(true); z = g;
      } else {
        if (z == p.right) {            // C: inside (LR)
          z = p; leftRotate(z);
        }
        p.setRed(false); g.setRed(true); // B: outside (LL)
        rightRotate(g);
      }
    } else { // mirror
      Node u = g.left;
      if (u.isRed()) {
        p.setRed(false); u.setRed(false);
        g.setRed(true); z = g;
      } else {
        if (z == p.left) {             // C: inside (RL)
          z = p; rightRotate(z);
        }
        p.setRed(false); g.setRed(true); // B: outside (RR)
        leftRotate(g);
      }
    }
  }
  root.setRed(false); // 루트는 항상 Black
}

왜 이렇게 하면 되나? (한 줄 직관)

u=Red: 색만 위로 “밀어올려”(split과 유사) 아래 위반 제거

u=Black + 바깥: g 기준 1회 회전으로 p를 위로 올려 Red-Red 끊기 + 흑높이 보존

u=Black + 안쪽: 부모 1회로 바깥화 → g 1회 + 재색칠로 마무리

마지막에 root Black 보장

NIL(센티넬) 한 줄 메모

항상 Black, 트리의 모든 “없음”을 NIL 하나로 통일

회전/보정 로직에서 null 분기 없음, 흑높이 계산이 명료

초기에는 root = NIL, root.parent = NIL

시간 복잡도

탐색/삽입/삭제: O(log n)

삽입/삭제에서 회전은 상수회(최대 2회)

자바 표준 컬렉션과 RBT

RBT 기반: TreeMap, TreeSet

버킷 트리화 시 RBT 사용(JDK 8+): HashMap, LinkedHashMap, ConcurrentHashMap (충돌 심한 버킷만)

빠른 체크리스트

 루트 Black, NIL Black

 Red의 자식은 모두 Black (연속 Red 금지)

 모든 루트→NIL 경로의 흑높이 동일

 회전 후 부모/자식 포인터 정확히 갱신

 fix 끝에서 root.setRed(false)

참고용 작은 그림(안쪽→바깥 변환)

LR

    g           g           z
   /           /           / \
  p     →     z     →     p   g
   \         /
    z       p


RL (미러)

  g             g             z
   \             \           / \
    p     →       z   →     g   p
   /               \
  z                 p


회전 순서만 외우면 모든 케이스를 빠르게 처리할 수 있습니다.