package edu.alexander.search;

import java.util.ArrayList;
import java.util.List;
/* 
 * @author Alexander Alexeev
 * source https://proglib.io/p/6-search-algorithms-java
 */
public class Searcher {
	// линейный поиск
	public static int linearSearch(int arr[], int elementToSearch) {

		for (int index = 0; index < arr.length; index++) {
			if (arr[index] == elementToSearch)
				return index;
		}
		return -1;
	}

	// метод вывода результата
	public static void print(int elementToSearch, int index) {
		if (index == -1) {
			System.out.println(elementToSearch + " not found.");
		} else {
			System.out.println(elementToSearch + " found at index: " + index);
		}
	}

	public static int binarySearch(int arr[], int elementToSearch) {

		int firstIndex = 0;
		int lastIndex = arr.length - 1;

		// условие прекращения (элемент не представлен)
		while (firstIndex <= lastIndex) {
			int middleIndex = (firstIndex + lastIndex) / 2;
			// если средний элемент - целевой элемент, вернуть его индекс
			if (arr[middleIndex] == elementToSearch) {
				return middleIndex;
			}

			// если средний элемент меньше
			// направляем наш индекс в middle+1, убирая первую часть из рассмотрения
			else if (arr[middleIndex] < elementToSearch)
				firstIndex = middleIndex + 1;

			// если средний элемент больше
			// направляем наш индекс в middle-1, убирая вторую часть из рассмотрения
			else if (arr[middleIndex] > elementToSearch)
				lastIndex = middleIndex - 1;

		}
		return -1;
	}

	// рекурсивный бинарный поиск
	public static int recursiveBinarySearch(int arr[], int firstElement, int lastElement, int elementToSearch) {

		// условие прекращения
		if (lastElement >= firstElement) {
			int mid = firstElement + (lastElement - firstElement) / 2;

			// если средний элемент - целевой элемент, вернуть его индекс
			if (arr[mid] == elementToSearch)
				return mid;

			// если средний элемент больше целевого
			// вызываем метод рекурсивно по суженным данным
			if (arr[mid] > elementToSearch)
				return recursiveBinarySearch(arr, firstElement, mid - 1, elementToSearch);

			// также, вызываем метод рекурсивно по суженным данным
			return recursiveBinarySearch(arr, mid + 1, lastElement, elementToSearch);
		}

		return -1;
	}

	// Алгоритм Кнута – Морриса – Пратта
	// метод лля запуска алгоритма
	public void launchKMPSearch() {
		String pattern = "AAABAAA";  
		String text = "ASBNSAAAAAABAAAAABAAAAAGAHUHDJKDDKSHAAJF";

		List<Integer> foundIndexes = performKMPSearch(text, pattern);

		if (foundIndexes.isEmpty()) {  
		    System.out.println("Pattern not found in the given text String"); 	
		} else {
		    System.out.println("Pattern found in the given text String at positions: " + .stream().map(Object::toString).collect(Collectors.joining(", ")));
		}
	}

	// главный метод алгоритма
	public List<Integer> performKMPSearch(String text, String pattern) {
		int[] compliedPatternArray = compilePatternArray(pattern);

		int textIndex = 0;
		int patternIndex = 0;

		List<Integer> foundIndexes = new ArrayList<>();

		while (textIndex < text.length()) {
			if (pattern.charAt(patternIndex) == text.charAt(textIndex)) {
				patternIndex++;
				textIndex++;
			}
			if (patternIndex == pattern.length()) {
				foundIndexes.add(textIndex - patternIndex);
				patternIndex = compliedPatternArray[patternIndex - 1];
			}

			else if (textIndex < text.length() && pattern.charAt(patternIndex) != text.charAt(textIndex)) {
				if (patternIndex != 0)
					patternIndex = compliedPatternArray[patternIndex - 1];
				else
					textIndex = textIndex + 1;
			}
		}
		return foundIndexes;
	}

	// метод используемый алгоритмом КМП
	public int[] compilePatternArray(String pattern) {
		int patternLength = pattern.length();
		int len = 0;
		int i = 1;
		int[] compliedPatternArray = new int[patternLength];
		compliedPatternArray[0] = 0;

		while (i < patternLength) {
			if (pattern.charAt(i) == pattern.charAt(len)) {
				len++;
				compliedPatternArray[i] = len;
				i++;
			} else {
				if (len != 0) {
					len = compliedPatternArray[len - 1];
				} else {
					compliedPatternArray[i] = len;
					i++;
				}
			}
		}
		System.out.println("Compiled Pattern Array " + Arrays.toString(compliedPatternArray));
		return compliedPatternArray;
	}

	// поиск с прыжками
	public static int jumpSearch(int[] integers, int elementToSearch) {

		int arrayLength = integers.length;
		int jumpStep = (int) Math.sqrt(integers.length);
		int previousStep = 0;

		while (integers[Math.min(jumpStep, arrayLength) - 1] < elementToSearch) {
			previousStep = jumpStep;
			jumpStep += (int) (Math.sqrt(arrayLength));
			if (previousStep >= arrayLength)
				return -1;
		}
		while (integers[previousStep] < elementToSearch) {
			previousStep++;
			if (previousStep == Math.min(jumpStep, arrayLength))
				return -1;
		}

		if (integers[previousStep] == elementToSearch)
			return previousStep;
		return -1;
	}

	// Интерполяционный поиск
	// Метод для запуска алгоритма
	public void launchInterpolationSearch() {
		int index = interpolationSearch(new int[] { 1, 2, 3, 4, 5, 6, 7, 8 }, 6);
		print(67, index);
	}

	public int interpolationSearch(int[] integers, int elementToSearch) {

		int startIndex = 0;
		int lastIndex = (integers.length - 1);

		while ((startIndex <= lastIndex) && (elementToSearch >= integers[startIndex])
				&& (elementToSearch <= integers[lastIndex])) {
			// используем формулу интерполяции для поиска возможной лучшей позиции для
			// существующего элемента
			int pos = startIndex + (((lastIndex - startIndex) / (integers[lastIndex] - integers[startIndex]))
					* (elementToSearch - integers[startIndex]));

			if (integers[pos] == elementToSearch)
				return pos;

			if (integers[pos] < elementToSearch)
				startIndex = pos + 1;

			else
				lastIndex = pos - 1;
		}
		return -1;
	}
	
		// Экспоненциальный поиск - пример на Java
		// етод запуска экспоненциального поиска
		public void lauchtExponentialSearch() {
			int index = exponentialSearch(new int[] { 3, 22, 27, 47, 57, 67, 89, 91, 95, 99 }, 67);
			print(67, index);
		}
	
		public int exponentialSearch(int[] integers, int elementToSearch) {
	
			if (integers[0] == elementToSearch)
				return 0;
			if (integers[integers.length - 1] == elementToSearch)
				return integers.length;
	
			int range = 1;
	
			while (range < integers.length && integers[range] <= elementToSearch) {
				range = range * 2;
			}
	
			return Arrays.binarySearch(integers, range / 2, Math.min(range, integers.length), elementToSearch);
		}

}
