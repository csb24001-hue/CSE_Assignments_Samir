
"""
Activity Log Analyzer

Assignment 7 implementation
Author: ChatGPT

Includes:
1. total_time_per_user
2. most_active_users
3. unique_actions
4. Complexity analysis (comments)
"""

from typing import List, Dict, Set
from functools import reduce
from collections import defaultdict


def total_time_per_user(logs: List[Dict]) -> Dict[str, float]:
    """
    Returns total screen time per user using reduce.
    """

    def reducer(acc: Dict[str, float], log: Dict) -> Dict[str, float]:
        acc[log["user"]] = acc.get(log["user"], 0.0) + float(log["duration"])
        return acc

    return reduce(reducer, logs, {})


def most_active_users(logs: List[Dict], k: int) -> List[str]:
    """
    Returns top k users by total activity time.
    """

    totals = total_time_per_user(logs)

    # sort users by total time descending
    sorted_users = sorted(totals.items(), key=lambda x: x[1], reverse=True)

    return [user for user, _ in sorted_users[:k]]


def unique_actions(logs: List[Dict]) -> Set[str]:
    """
    Returns all unique actions using set comprehension.
    """
    return {log["action"] for log in logs}


"""
Complexity Analysis:

Let:
n = number of logs
u = number of unique users

1. total_time_per_user:
   Time: O(n)
   Space: O(u)

2. most_active_users:
   Time: O(n + u log u)  (aggregation + sorting)
   Space: O(u)

3. unique_actions:
   Time: O(n)
   Space: O(a) where a = unique actions
"""
