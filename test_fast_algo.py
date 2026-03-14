def get_subset(m, S):
    if S == 0:
        return []
    
    valid_k = -1
    for k in range(m, 0, -1):
        if (k % 2) == (S % 2):
            min_sum = k * k
            max_sum = k * (2 * m - k)
            if min_sum <= S <= max_sum:
                valid_k = k
                break
    
    if valid_k == -1:
        return None
        
    subset = []
    k = valid_k
    for i in range(m, 0, -1):
        x = 2 * i - 1
        if k > 0 and S - x >= (k - 1) * (k - 1):
            subset.append(i)
            S -= x
            k -= 1
            if k == 0:
                break
    return subset

def solve(n, h):
    if h < 2 * n - 1 or h > n * n:
        return "impossible"
        
    # Try Base Stack: C, n, B
    target_C = h - (2 * n - 1)
    C = get_subset(n - 1, target_C)
    if C is not None:
        C_set = set(C)
        C_list = sorted(list(C_set))
        B_list = sorted([x for x in range(1, n) if x not in C_set], reverse=True)
        seq = C_list + [n] + B_list
        return seq
        
    # Try Inner Stack: n, A, B
    target_A_prime = h - (2 * n - 2)
    A_prime = get_subset(n - 2, target_A_prime)
    if A_prime is not None:
        A_prime_set = set(A_prime)
        A_list = sorted(list(A_prime_set) + [n - 1])
        B_list = sorted([x for x in range(1, n - 1) if x not in A_prime_set], reverse=True)
        seq = [n] + A_list + B_list
        return seq
        
    return "impossible"

print(solve(4, 9))
print(solve(4, 100))
print(solve(6, 13))
