/*
MICP Summer 2017
Author: Xinyi(Cindy) Chen
Language: Java
*/
/**********WEEK TWO***********/

/*
3. Longest Substring Without Repeating Characters
Given a string, find the length of the longest substring without repeating characters.
Examples:
Given "abcabcbb", the answer is "abc", which the length is 3.
Given "bbbbb", the answer is "b", with the length of 1.
Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
*/
/*
1. examples
abcabcbb	abc 	3
bbbbb		b 		1
pwwkew		wke 	3

explaining ideas using examples:
a b c a b c b b
0 1 2 3 4 5 6 7
          p q
map(a 3, b 6, c 5)
cur 2
maxlen 3

p w w k e w
0 1 2 3 4 5
          pq
map(p 0, w 5, k 3, e 4)
curlen 3
maxlen 3

2. Pseudocode
2.1 brute force: find the longest substring starts with each character
longestSubstring(str):
	iter char in str:
		find the longest substring without repeating characters starts with this char

2.2 optimize: two pointers
longestSubstring(str):
	p = 0, q = 1
	map.put(str[0],0)
	map.put(str[1],1)
	while (p <= q):
		if map contains key str[q]:
			move p to str[q]'s next position
			update str[q]'s position
		else
			put str[q] and it's position into map
	update length;

3. Implementation and Test
See the following unannotated code.
*/
public class Solution{
	public int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) return 0;
		Map<Character, Integer> position = new HashMap<>();
		int curlen = 0, maxlen = 0;
		
		for (int i = 0, j = 0; j < s.length(); j++){
			char c = s.charAt(j);
			if (position.containsKey(c)) {
				i = position.get(c) + 1;
			}
			position.put(c, j);
			curlen = j - i + 1;
			maxlen = Math.max(curlen, maxlen);
		}
		return maxlen;
	}

	@Test /*All Pass*/
	public void testLengthOfLongestSubstring() {
		assertEquals(3, lengthOfLongestSubstring("abcabcbb"));
		assertEquals(1, lengthOfLongestSubstring("bbbbbbb"));//repeating chars
		assertEquals(3, lengthOfLongestSubstring("pwwkew"));
		assertEquals(0, lengthOfLongestSubstring(""));//empty string
		assertEquals(0, lengthOfLongestSubstring(null));//null
		assertEquals(4, lengthOfLongestSubstring("23dad3"));//with digits
		assertEquals(6, lengthOfLongestSubstring("%4a.&d.3"));//special characters
	}
}