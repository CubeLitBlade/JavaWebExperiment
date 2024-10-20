package experiment;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;


public class Test {
    /**
     * 从控制台中读取 10 个整数，将其按照升序排序，并且查找目标整数。
     * <p>方法定义在 {@link Solution#sort(int[])} 和 {@link Solution#binarySearch(int[], int)}。</p>
     */
    public static void runExperiment1_1(){
        Scanner sc = new Scanner(System.in);
        int key = 0;
        int result;

        System.out.println("Enter 10 Integers to go: ");
        int[] n = new int[10];
        for(int i = 0; i < n.length; i++) {
            if (sc.hasNextInt()) {
                n[i] = sc.nextInt();
            }
        }
        Solution.sort(n);   // 调用 Solution.sort 方法，对这些整数进行冒泡排序。

        System.out.println("Which integer do you want? ");
        if(sc.hasNextInt()) {
            key = sc.nextInt();
        }
        result = Solution.binarySearch(n, key); // 调用 Solution 中的 binarySearch 方法，利用二分查找查询需要的整数。

        System.out.println("The array above is arranged in ascending order: ");
        System.out.println(Arrays.toString(n));

        // 由于在 Solution.binarySearch 中，没有找到元素时会返回 -1， 在此对其进行输出上的处理。
        if(result != -1){
            System.out.printf("The index of %d is %d. %n", key, result);
        }
        else {
            System.out.printf("The index of %d is not found. %n", key);
        }
        sc.close();
    }

    /**
     * 给定若干个日期，进行排序。
     * <p>方法定义在 {@link Solution#sort(Date[])} 。</p>
     */
    public static void runExperiment1_2(){
        // 由于日期不便使用控制台输入，在此指定若干个日期进行检测。
        LocalDate[] localDates = {
                LocalDate.of(2004, 7, 28),
                LocalDate.of(2012, 12, 28),
                LocalDate.of(2015, 12, 28),
                LocalDate.of(2013, 12, 28),
                LocalDate.of(2021, 12, 26),
                LocalDate.of(2016, 12, 28),
                LocalDate.of(2018, 10, 1),
                LocalDate.of(2017, 12, 26),
                LocalDate.of(2018, 10, 1),
                LocalDate.of(2021, 12, 26),
                LocalDate.of(2023, 12, 20),
                LocalDate.of(2017, 12, 26)
        };

        // 借用 LocalDates 类对 Dates 类进行初始化，从而简化整个初始化过程。
        Date[] dates = new Date[localDates.length];
        for(int i = 0; i < dates.length; i++) {
            dates[i] = Date.from(localDates[i].atStartOfDay(ZoneId.of("Asia/Shanghai")).toInstant());
        }
        System.out.println("Before sorting: ");
        for(Date i : dates){
            System.out.printf("%tF%n", i);  // 格式化输出日期。%tF 指示 yyyy-MM-dd 的格式。
        }
        Solution.sort(dates);   // 调用 Solution.sort 方法，对这些日期进行冒泡排序。
        System.out.println("After sorting: ");
        for(Date i : dates){
            System.out.printf("%tF%n", i);  // 格式化输出日期。%tF 指示 yyyy-MM-dd 的格式。
        }
    }

    /**
     * 使用链表解决约瑟夫环问题。
     * <p>方法定义在 {@link Solution#JosephusCircle(int, int)} 。</p>
     */
    public static void runExperiment1_3(){
        int n = 0, k = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("How many people are caught in Josephus Circle? ");
        if(sc.hasNextInt()) {
            n = sc.nextInt();
        }
        System.out.println("I wonder how many steps does it take to kill someone. ");
        if(sc.hasNextInt()) {
            k = sc.nextInt();
        }

        System.out.println("Player " + Solution.JosephusCircle(n, k) + " was left all alone, no one else to blame. ");
        System.out.println("In the end, he met his doom, and then there were none. ");
    }

    /**
     * 使用递归输出文件夹及其子文件夹中的所有文件。
     * <p>用户不需要指定递归深度，对方法进行了封装，封装后的方法定义在 {@link Solution#tree(String)} 。</p>
     */
    public static void runExperiment1_4() {
        System.out.println("输出./test/exp1-4的目录结构如下：");
        try {
            Solution.tree("./test/exp1-4");
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    /**
     * 遍历并输出数组中的元素。
     * <p>方法返回了遍历后的字符串，定义在 {@link Solution#toString(Object[])} 。</p>
     */
    public static void runExperiment1_5(){
        Integer[] n = new Integer[10];
        for(int i = 0; i < n.length; i++) {
            n[i] = i;   // 初始化数组，自动打包。
        }
        System.out.println(Solution.toString(n));
    }

    /**
     * 遍历并输出 {@code java.util.List} 中的元素。
     * <p>方法返回了遍历后的字符串，定义在 {@link Solution#toString(List)} 。</p>
     */
    public static void runExperiment1_6(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++) {
            list.add(i);    // 初始化列表，自动打包。
        }
        System.out.println(Solution.toString(list));
    }

    /**
     * 遍历并输出 {@code java.util.Map} 中的键值对。
     * <p>方法返回了遍历后的字符串，定义在 {@link Solution#toString(Map)} 。</p>
     */
    public static void runExperiment1_7(){
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        map.put(4, "Date");
        map.put(5, "Elderberry");

        System.out.println(Solution.toString(map));
    }

    /**
     * 分别使用字符流和字节流复制文件。
     * <p>方法定义在 {@link Solution#copyTextFile(String, String)} 和 {@link Solution#copyFile(String, String)}</p>
     */
    public static void runExperiment1_8(){
        System.out.println("使用字符流将 ./test/exp1-7/text-source-file 复制到 ./test/exp1-7/text-copied-file");
        try {
            Solution.copyTextFile("./test/exp1-7/text-source-file", "./test/exp1-7/text-copied-file");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("使用字节流将 ./test/exp1-7/mp3-source-file.mp3 复制到 ./test/exp1-7/mp3-copied-file.mp3");
        try {
            Solution.copyTextFile("./test/exp1-7/mp3-copied-file.mp3", "./test/exp1-7/mp3-copied-file.mp3");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * 分别使用字符流和字节流复制文件，并对可能出现的异常进行处理。
     * <p>方法定义在 {@link Solution#copyTextFile(String, String)} 和 {@link Solution#copyFile(String, String)}</p>
     */
    public static void runExperiment1_8_full() {
        System.out.println("由于此方法涉及的情况较为复杂，测试程序在此分类讨论。");

        System.out.println("测试：当源路径指示一个文件夹而非文件时，将会报错。");
        try {
            Solution.copyTextFile("./test/exp1-7", "./test/exp1-7/is-a-directory.txt");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("测试：当源路径和目标路径完全一致时，将会报错。");
        try {
            Solution.copyTextFile("./test/exp1-7/text-source-file", "./test/exp1-7/text-source-file");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("测试：当源路径指示的文件不存在时，将会报错。");
        try {
            Solution.copyTextFile("./test/exp1-7/no-such-file-or-directory", "./test/exp1_7");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("测试：当源路径和目标路径指示正确时，正常复制。");
        try {
            Solution.copyTextFile("./test/exp1-7/text-source-file", "./test/exp1-7/text-copied-file");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("测试：当目标路径指示一个文件夹时，正常复制，复制文件的文件名与源文件相同。");
        try {
            Solution.copyTextFile("./test/exp1-7/text-source-file", "./test/exp1-7/destination");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("测试：使用字节流复制二进制文件，其实现与以上字符流类似，只展示正常情况");
        try {
            Solution.copyFile("./test/exp1-7/mp3-source-file.mp3", "./test/exp1-7/mp3-copied-file.mp3");
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
