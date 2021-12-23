package edu.alexander.sorting;

import java.util.Arrays;

public class Sorter {
	// Сортировка методом пузырька
	public void bubbleSort(int[] array) {
		boolean sorted = false;
		int iterationCounter = 0;
		while (!sorted) {
			sorted = true;
			for (int i = 0; i < array.length - 1; i++) {
				iterationCounter++;
				if (array[i] > array[i + 1]) {
					int swapNum = array[i];
					array[i] = array[i + 1];
					array[i + 1] = swapNum;
					sorted = false;
				}
			}
		}
		System.out.println("Iterations " + iterationCounter);
	}

	// Сортировка вставками
	public void insertionSort(int[] arr) {
		int iterations = 0;
		for (int i = 0; i < arr.length; i++) {
			int current = arr[i];
			int j = i - 1;
			while (j > 0 && current < arr[j]) {
				arr[j + 1] = arr[j];
				j--;
				iterations++;
			}
			arr[j + 1] = current;
		}
		System.out.println("Iterations count " + iterations);
	}

	// Сортировка выбором
	public void selectionSort(int[] arr) {
		for (int left = 0; left < arr.length; left++) {
			int minInd = left;
			for (int i = left; i < arr.length; i++) {
				if (arr[i] < arr[minInd]) {

				}
			}
		}
	}

	// Челночная сортировка
	public void shuttleSort(int[] array) {
		System.out.println(Arrays.toString(array));
		for (int i = 1; i < array.length; i++) {
			if (array[i] < array[i - 1]) {
				swap(array, i, i - 1);
				for (int z = i - 1; (z - 1) >= 0; z--) {
					if (array[z] < array[z - 1]) {
						swap(array, z, z - 1);
					} else {
						break;
					}
				}
			}
		}
		System.out.println(Arrays.toString(array));
	}

	public void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (i != arr.length - 1)
				System.out.print(arr[i] + ",");
			else
				System.out.print(arr[i] + "\r\n");
		}
	}

	// операция замены
	public void swap(int[] arr, int index1, int index2) {
		int temp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = temp;
	}

	// инициализация массива
	public int[] initializeArray() {
		int[] retval = { 1, 5, 2, 3, 7, 6, 12, 45, 8, 0 };
		return retval;
	}

	// сортировка методом шелла
	public void shellSort(int[] array) {
		System.out.println(Arrays.toString(array));
		// Высчитываем промежуток между проверяемыми элементами
		int gap = array.length / 2;
		// Пока разница между элементами есть
		while (gap >= 1) {
			for (int right = 0; right < array.length; right++) {
				// Смещаем правый указатель, пока не сможем найти такой, что
				// между ним и элементом до него не будет нужного промежутка
				for (int c = right - gap; c >= 0; c -= gap) {
					if (array[c] > array[c + gap]) {
						swap(array, c, c + gap);
					}
				}
			}
			// Пересчитываем разрыв
			gap = gap / 2;
		}
		System.out.println(Arrays.toString(array));

	}
	//Сортировка слиянием
	// вызов метода MergeSort осуществляется через mergeSort(array, 0,
	// array.length-1)
	// рекурсивный метод mergeSort
	public void mergeSortRec(int[] source, int left, int right) {
		// Выберем разделитель, т.е. разделим пополам входной массив
		int delimiter = left + ((right - left) / 2) + 1;
		// Выполним рекурсивно данную функцию для двух половинок (если сможем разбить(
		if (delimiter > 0 && right > (left + 1)) {
			mergeSortRec(source, left, delimiter - 1);
			mergeSortRec(source, delimiter, right);
		}
	}

	// нерекурсивныый метод mergeSort
	public void mergeSort(int[] source, int left, int right) {
		// Выберем разделитель, т.е. разделим пополам входной массив
		int delimiter = left + ((right - left) / 2) + 1;
		// Выполним рекурсивно данную функцию для двух половинок (если сможем разбить(
		if (delimiter > 0 && right > (left + 1)) {
			mergeSort(source, left, delimiter - 1);
			mergeSort(source, delimiter, right);
		}
		// Создаём временный массив с нужным размером
		int[] buffer = new int[right - left + 1];
		// Начиная от указанной левой границы идём по каждому элементу
		int cursor = left;
		for (int i = 0; i < buffer.length; i++) {
			// Мы используем delimeter чтобы указывать на элемент из правой части
			// Если delimeter > right, значит в правой части не осталось недобавленных
			// элементов
			if (delimiter > right || source[cursor] > source[delimiter]) {
				buffer[i] = source[cursor];
				cursor++;
			} else {
				buffer[i] = source[delimiter];
				delimiter++;
			}
		}
		System.arraycopy(buffer, 0, source, left, buffer.length);
	}

	// сортировка подсчетом
	public static int[] countingSort(int[] theArray, int maxValue) {
		// Массив со "счётчиками" размером от 0 до максимального значения
		int numCounts[] = new int[maxValue + 1];
		// В соответствующей ячейке (индекс = значение) увеличиваем счётчик
		for (int num : theArray) {
			numCounts[num]++;
		}
		// Подготавливаем массив для отсортированного результата
		int[] sortedArray = new int[theArray.length];
		int currentSortedIndex = 0;
		// идём по массиву со "счётчиками"
		for (int n = 0; n < numCounts.length; n++) {
			int count = numCounts[n];
			// идём по количеству значений
			for (int k = 0; k < count; k++) {
				sortedArray[currentSortedIndex] = n;
				currentSortedIndex++;
			}
		}
		return sortedArray;
	}

	// быстрая сортировка
	public static void quickSort(int[] source, int leftBorder, int rightBorder) {
		int leftMarker = leftBorder;
		int rightMarker = rightBorder;
		int pivot = source[(leftMarker + rightMarker) / 2];
		do {
			// Двигаем левый маркер слева направо пока элемент меньше, чем pivot
			while (source[leftMarker] < pivot) {
				leftMarker++;
			}
			// Двигаем правый маркер, пока элемент больше, чем pivot
			while (source[rightMarker] > pivot) {
				rightMarker--;
			}
			// Проверим, не нужно обменять местами элементы, на которые указывают маркеры
			if (leftMarker <= rightMarker) {
				// Левый маркер будет меньше правого только если мы должны выполнить swap
				if (leftMarker < rightMarker) {
					int tmp = source[leftMarker];
					source[leftMarker] = source[rightMarker];
					source[rightMarker] = tmp;
				}
				// Сдвигаем маркеры, чтобы получить новые границы
				leftMarker++;
				rightMarker--;
			}
		} while (leftMarker <= rightMarker);

		// Выполняем рекурсивно для частей
		if (leftMarker < rightBorder) {
			quickSort(source, leftMarker, rightBorder);
		}
		if (leftBorder < rightMarker) {
			quickSort(source, leftBorder, rightMarker);
		}
	}

	public static void main(String[] args) {

		Sorter sorter = new Sorter();
		int[] arr = sorter.initializeArray();
		System.out.println("Bubble sort");
		sorter.printArray(arr);
		sorter.bubbleSort(arr);
		sorter.printArray(arr);
		arr = sorter.initializeArray();
		System.out.println("Insertion Sort");
		sorter.printArray(arr);
		sorter.insertionSort(arr);
		sorter.printArray(arr);

	}
}
