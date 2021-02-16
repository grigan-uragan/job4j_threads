package ru.job4j.pool;

public class ObjectSearch<T> {
    private final T[] data;

    public ObjectSearch(T[] data) {
        this.data = data;
    }

    public int getIndex(T element) {
        return getIndex(element, 0, data.length);
    }

    public int getIndex(T element, int start, int end) {
        if ((end - start) < 10) {
            for (int i = start; i < end; i++) {
                if (element.equals(data[i])) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (start + end) / 2;
        int result = getIndex(element, start, mid);
        if (result != -1) {
            return result;
        }
        return getIndex(element, mid, end);
    }

    public static void main(String[] args) {
        String string = "Lorem ipsum dolor sit amet, consectetur adipiscing elit."
                + " Aenean eget hendrerit nibh. Duis eget nunc dui."
                + " Donec quis tellus vitae nulla scelerisque ultrices."
                + " Pellentesque vel lectus arcu."
                + " Curabitur blandit aliquam erat vitae volutpat."
                + " Praesent tristique magna odio, at pretium leo elementum et."
                + " In non ultrices velit."
                + " Aliquam erat volutpat."
                + " Fusce turpis risus, aliquet eu mattis eu, tristique et risus."
                + " Quisque mollis et tortor condimentum commodo."
                + " Nulla et malesuada orci, quis convallis nulla."
                + " Sed quis nisl in ante ultrices malesuada et sed enim."
                + " Nunc eget risus at diam convallis lobortis."
                + " Mauris in orci sagittis, finibus tellus vitae, molestie mauris."
                + " Vestibulum eget aliquam nisl.";
        String[] strings = string.split("\\s");
        ObjectSearch<String> objectSearch = new ObjectSearch<>(strings);

        System.out.println(objectSearch.getIndex("Fusce"));
    }
}
