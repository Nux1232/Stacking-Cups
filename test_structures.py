import itertools

def calc(p):
    T = {}
    B = {}
    for i, cup in enumerate(p):
        max_B = 0
        max_T = 0
        for j in range(i):
            if cup < p[j]:
                max_B = max(max_B, B[p[j]] + 1)
            else:
                max_T = max(max_T, T[p[j]])
        B[cup] = max(max_B, max_T)
        T[cup] = B[cup] + 2*cup - 1
    return max(T.values())

def solve(n, h):
    # Try Base Stack: C, n, B
    target = h - (2*n - 1)
    if target >= 0:
        for k in range(1 << (n - 1)):
            C = []
            s = 0
            for i in range(1, n):
                if (k & (1 << (i - 1))):
                    C.append(i)
                    s += 2*i - 1
            if s == target:
                B_set = [x for x in range(1, n) if x not in C]
                B_set.sort(reverse=True)
                seq = C + [n] + B_set
                if calc(seq) == h:
                    return seq

    # Try Inner Stack: n, A, B
    target2 = h - 1
    if target2 >= (2*(n-1) - 1):
        for k in range(1 << (n - 2)):
            A = []
            s = 2*(n-1) - 1
            A.append(n-1)
            for i in range(1, n - 1):
                if (k & (1 << (i - 1))):
                    A.append(i)
                    s += 2*i - 1
            if s == target2:
                A.sort()
                B_set = [x for x in range(1, n-1) if x not in A]
                B_set.sort(reverse=True)
                seq = [n] + A + B_set
                if calc(seq) == h:
                    return seq
    return None

for n in range(1, 7):
    possible = set()
    for p in itertools.permutations(range(1, n+1)):
        possible.add(calc(p))
    
    for h in possible:
        res = solve(n, h)
        if res is None:
            print(f"FAILED for n={n}, h={h}")
    print(f"n={n} OK")
