# 합병 정렬(Merge Sort) — 기술 면접 템플릿 버전

> 한 줄 정의

* **분할 정복** 기반의 안정 정렬. 배열을 절반으로 재귀 분할한 뒤, **정렬된 두 리스트를 선형 시간에 병합**하여 전체를 정렬한다.

---

## 1) 핵심 아이디어 (직관)

* 큰 문제(전체 배열 정렬)를 **반으로 쪼개** 두 하위 배열을 정렬하고, **두 정렬 리스트를 병합**하면 전체가 정렬된다.
* 병합(merge)은 **두 포인터**로 앞쪽 요소부터 비교·선택하며 **O(n)** 에 끝난다.

**그림으로 떠올리기**: 분할 트리 (높이 ≈ log₂n). 각 레벨에서 전체 n개 원소가 병합으로 한 번씩 스캔된다 → 모든 레벨 비용 합이 **n log n**.

---

## 2) 절차(알고리즘 스텝)

### Top-Down(재귀)

1. 배열 길이 0·1이면 반환(기저).
2. 중간 인덱스로 좌/우 분할 → 각각 재귀 정렬.
3. 좌/우를 **보조 버퍼**에 병합하여 반환.

### Bottom-Up(반복)

1. 길이 1 구간들을 시작으로 **run size = 1, 2, 4, 8, ...** 두 배씩 확장.
2. 인접한 두 run을 병합하여 새로운 정렬 run 생성.
3. run size ≥ n이면 종료.

* 재귀 스택을 사용하지 않아 **스택 오버헤드 없음**, 캐시/메모리 배치 고려 용이.

---

## 3) 정확성 스케치

* 귀납적 주장: 길이 k < n인 배열은 올바르게 정렬된다고 가정.
* 크기 n 배열에서 좌/우가 정렬(귀납 가정)되면, 병합 단계에서 **항상 두 부분의 최솟값**을 앞에서부터 선택하므로 전체 순서가 유지된다.
* 안정성: 비교에 **≤ 대신 <** 을 사용하지 않고, 같을 때 **좌측을 먼저** 선택하면 **상대적 순서가 보존**된다.

---

## 4) 시간·공간 복잡도 & 특성

* **시간**: 최선=평균=최악 **O(n log n)** (분할 트리 높이 log n × 각 레벨 O(n)).
* **공간**: 일반 배열 구현은 **O(n)** 보조 버퍼 필요(제자리 정렬 아님). 링크드 리스트는 **O(1)** 추가 공간(포인터만 재연결).
* **안정성**: 구현에 따라 **안정 가능**(일반적으로 안정).
* **비교 기반** 정렬이며, 비교 횟수 하한 Ω(n log n)에 부합.

---

## 5) 언제 쓰나 (트레이드오프)

* **큰 데이터, 최악 케이스 보장 필요**: 퀵소트는 평균 빠르지만 최악 O(n²) → **합병 정렬은 항상 O(n log n)**.
* **안정 정렬이 필요**: 예) 키 정렬 후 같은 키에서 2차 기준 유지.
* **링크드 리스트 정렬**: 분할/병합이 포인터만 다루므로 매우 적합(제자리·안정).
* **외부 정렬(External sort)**: 디스크/네트워크 스트림에서 **병합 단계의 순차 접근**이 유리.

**언제 피하나**

* **메모리 제약이 심함**(배열용 보조 버퍼가 부담).
* **캐시 지역성**은 퀵소트가 우수한 경우가 많음(분할/스왑이 인‑플레이스).

---

## 6) 변형·실무 이슈

* **TimSort**: 실사용(파이썬, 자바 리스트 정렬). **자연스러운 runs**를 탐지해 인서션 + 병합. 안정/적응형.
* **Natural Merge Sort**: 이미 정렬된 runs를 탐지 후 병합.
* **In‑place Merge**: 보조 공간을 크게 줄이는 고급 기법(복잡·상수 크기 커짐, 구현 난도↑).
* **병렬화**: 좌/우 분할을 병렬 정렬, 이후 병합도 병렬 가능(스레드 풀/작업 절단 임계값 필요).

---

## 7) 흔한 실수

* 병합 시 **경계 조건**(한 쪽 소진 후 나머지 복사) 누락.
* **안정성 깨짐**: 같을 때 오른쪽 먼저 선택.
* **버퍼 재사용 실패**: 매 재귀마다 새 배열 할당 → GC/성능 저하. (한 개의 보조 버퍼를 **재사용** 권장)
* **오프바이원**: mid 계산, 반열린 구간([l, r)) 일관성 붕괴.

---

## 8) 면접 Q&A 샘플

**Q1. 왜 항상 O(n log n)인가요?**
A. 트리 높이 log n(각 분할) × 각 레벨에서 n개 원소가 정확히 한 번씩 병합에 스캔됨 → n·log n.

**Q2. 안정 정렬로 만들려면?**
A. 병합에서 같을 때 **좌측 우선**으로 선택. 혹은 비교함수에서 tie-breaker를 인덱스 순으로 처리.

**Q3. 링크드 리스트에선 왜 유리?**
A. **중간 찾기(O(n)) + 포인터 병합(O(n))** 으로 추가 버퍼 없이 가능. 스왑 비용 없음.

**Q4. 퀵소트 대비 장단점?**
A. 장점: **최악 O(n log n), 안정, 외부정렬 적합**. 단점: **추가 공간, 캐시 지역성 열세, 상수항↑**.

**Q5. 실서비스에선 왜 TimSort?**
A. 실제 데이터는 **부분 정렬(run)** 이 흔함. TimSort는 run을 활용해 **거의 정렬된 입력에서 O(n)** 에 수렴.

---

## 9) 의사코드 (Top‑Down)

```
MERGE-SORT(A, l, r):          # 정렬 범위 [l, r)
  if r - l <= 1: return
  m = (l + r) // 2
  MERGE-SORT(A, l, m)
  MERGE-SORT(A, m, r)
  MERGE(A, l, m, r)

MERGE(A, l, m, r):
  i = l, j = m
  tmp = []
  while i < m and j < r:
    if A[i] <= A[j]:        # 안정성 보장
      tmp.append(A[i]); i += 1
    else:
      tmp.append(A[j]); j += 1
  while i < m: tmp.append(A[i]); i += 1
  while j < r: tmp.append(A[j]); j += 1
  A[l:r] = tmp
```

---

## 10) 코드 스니펫 (JavaScript, 배열 — 재귀 & 반복)

### 재귀 Top‑Down

```js
function mergeSort(arr) {
  const a = arr.slice();
  const buf = new Array(a.length);
  function sort(l, r) {        // [l, r)
    if (r - l <= 1) return;
    const m = (l + r) >>> 1;
    sort(l, m);
    sort(m, r);
    merge(l, m, r);
  }
  function merge(l, m, r) {
    let i = l, j = m, k = l;
    while (i < m && j < r) {
      if (a[i] <= a[j]) buf[k++] = a[i++];
      else               buf[k++] = a[j++];
    }
    while (i < m) buf[k++] = a[i++];
    while (j < r) buf[k++] = a[j++];
    for (let t = l; t < r; t++) a[t] = buf[t];
  }
  sort(0, a.length);
  return a;
}
```

### 반복 Bottom‑Up

```js
function mergeSortIter(arr) {
  const a = arr.slice();
  const n = a.length;
  const buf = new Array(n);
  for (let size = 1; size < n; size <<= 1) {
    for (let l = 0; l < n; l += size << 1) {
      const m = Math.min(l + size, n);
      const r = Math.min(l + (size << 1), n);
      let i = l, j = m, k = l;
      while (i < m && j < r) {
        if (a[i] <= a[j]) buf[k++] = a[i++];
        else               buf[k++] = a[j++];
      }
      while (i < m) buf[k++] = a[i++];
      while (j < r) buf[k++] = a[j++];
      for (let t = l; t < r; t++) a[t] = buf[t];
    }
  }
  return a;
}
```

### 링크드 리스트(Merge Sort) 개요 (JS/의사)

* 중간 찾기: **fast/slow** 포인터로 분할.
* 재귀 정렬 후 **노드 포인터 병합**(추가 배열 불필요, 안정).

```js
function sortList(head) {
  if (!head || !head.next) return head;
  let slow = head, fast = head, prev = null;
  while (fast && fast.next) { prev = slow; slow = slow.next; fast = fast.next.next; }
  prev.next = null;                // split
  const l1 = sortList(head);
  const l2 = sortList(slow);
  return merge(l1, l2);
}
function merge(a, b) {
  const dummy = { val: 0, next: null };
  let cur = dummy;
  while (a && b) {
    if (a.val <= b.val) { cur.next = a; a = a.next; }
    else { cur.next = b; b = b.next; }
    cur = cur.next;
  }
  cur.next = a || b;
  return dummy.next;
}
```

---

## 11) 복잡도/특성 표

| 항목             | 값                |
| -------------- | ---------------- |
| 최선/평균/최악 시간    | O(n log n)       |
| 추가 공간(배열)      | O(n)             |
| 추가 공간(링크드 리스트) | O(1)             |
| 안정성            | 구현에 따라 안정(보통 안정) |
| 인‑플레이스         | 일반적으론 아님         |
| 외부정렬 적합성       | 높음               |

---

## 12) 연습 문제(추천)

* LeetCode 148. Sort List — **LL 합병 정렬 필수**
* LeetCode 912. Sort an Array — 다양한 정렬 중 **merge sort 구현**
* 외부 정렬/다중 리스트 병합: LeetCode 23. Merge k Sorted Lists (아이디어 확장)

---

## 13) 면접 마무리 멘트(샘플)

* “합병 정렬은 **항상 O(n log n)**, **안정**, **외부정렬/링크드리스트에 강함**이 핵심입니다. 상용 환경에서는 **TimSort**처럼 **자연 runs**를 활용한 적응형 변형이 실효적이며, 메모리/캐시 제약이 크면 퀵소트·힙소트와의 **트레이드오프**를 설명하겠습니다.”
