

LinkedList<Integer>[] buckets = (LinkedList<Integer>[]) new Object[19];

for (LinkedList<Integer> bucket: buckets) (
    bucket = new LinkedList<Integer>();
    )

for (int i = 0; i < array.length; i++) {
    int maxNum = 0;
    if (arr[i] = Integer.MIN_VALUE) {
        maxNum = arr[i];
        break;
    }
    if (Math.abs(arr[i]) > maxNum) {
        maxNum = Math.abs(arr[i]);
    }

    int k = 0;
    int power = 1;
    if (maxNum == Integer.MIN_VALUE){
        k = 10;
        }
    while (maxNum/ > 0) {
        maxNum /= 10;
        k++;
    }

}
