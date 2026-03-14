def can_form_greedy(target, items):
    subset = []
    for x in items:
        if target >= x:
            target -= x
            subset.append(x)
    return target == 0, subset

def test_greedy():
    for n in range(1, 15):
        items = [2*i - 1 for i in range(n-1, 0, -1)]
        for k in range(1 << (n - 1)):
            s = 0
            for i in range(n - 1):
                if (k & (1 << i)):
                    s += items[i]
            # Since s is achievable, let's see if greedy finds IT OR ANOTHER VALID SUBSET
            ok, _ = can_form_greedy(s, items)
            if not ok:
                print(f"FAILED greedy for n={n}, sum={s}")
                return
    print("Greedy works!")
test_greedy()
