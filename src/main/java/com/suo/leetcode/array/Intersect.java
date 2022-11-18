package com.suo.leetcode.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * 给你两个整数数组 nums1 和 nums2 ，请你以数组形式返回两数组的交集。返回结果中每个元素出现的次数，
 * 应与元素在两个数组中都出现的次数一致（如果出现次数不一致，则考虑取较小值）。可以不考虑输出结果的顺序。

示例 1：
输入：nums1 = [1,2,2,1], nums2 = [2,2]
输出：[2,2]
示例 2:

输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
输出：[4,9]
 
提示：
1 <= nums1.length, nums2.length <= 1000
0 <= nums1[i], nums2[i] <= 1000

链接：https://leetcode.cn/leetbook/read/top-interview-questions-easy/x2y0c2/

 * @author suozq
 *  见解： 有两种方法，一种是排序；一种是使用map; 在num1和num2的长度都不是特别长时，使用排序效率高；在长度过长时，排序效率就不行了
 */
public class Intersect {
	
	
	public static void put(HashMap<Integer,Integer> map,Integer k) {
		if(map.containsKey(k)) {
    		Integer v = map.get(k);
    		map.put(k, v+1);
    	}else {
    		map.put(k, 1);
    	}
	}
	
	public static void remove(HashMap<Integer,Integer> map,Integer k) {
		if(map.containsKey(k)) {
    		Integer v = map.get(k);
    		if(v==1) {
    			map.remove(k);
    		}else {
    			map.put(k, v-1);
    		}
    	}
	}
	
	public static int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i : nums1) {
        	put(map,i);
        }
        ArrayList<Integer> list = new ArrayList<>();
        for(int i : nums2) {
        	if(map.containsKey(i)) {
        		list.add(i);
        		remove(map, i);
        	}
        }
        return list.parallelStream().mapToInt(Integer::intValue).toArray();
    }
	
	public static int[] intersect2(int[] nums1, int[] nums2) {
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		int[] r = new int[nums1.length];
		int k=0;
		for(int i=0,j=0;i<nums1.length&&j<nums2.length;) {
			if(nums1[i]>nums2[j]) {
				j++;
			}else if(nums1[i]<nums2[j]) {
				i++;
			}else {
				r[k++]=nums1[i];
				i++;
				j++;
			}
		}
		return Arrays.copyOfRange(r, 0, k);
	}
}
