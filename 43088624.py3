N = int(input())
wall_list = [0] * 1001
max_h = 0
max_h_idx = 0
end_idx = 0
for _ in range(N):
    idx, h = map(int, input().split())
    wall_list[idx] = h
    if max_h < h:
        max_h = h
        max_h_idx = idx
    end_idx = max(end_idx, idx)

answer = 0
stack = []
for i in range(0, max_h_idx+1):
    if not stack:
        stack.append(wall_list[i])
        answer += stack[-1]
    else:
        if stack[-1] < wall_list[i]:
            stack.pop()
            stack.append(wall_list[i])
        answer += stack[-1]

stack = []
for i in range(end_idx, max_h_idx, -1):
    if not stack:
        stack.append(wall_list[i])
        answer += stack[-1]
    else:
        if stack[-1] < wall_list[i]:
            stack.pop()
            stack.append(wall_list[i])
        answer += stack[-1]

print(answer)