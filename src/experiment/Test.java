package experiment;

import java.io.FileNotFoundException;
import java.io.IOException;
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
     * 给定若干日期，进行排序。
     * <p>方法定义在 {@link Solution#sort(Date[])} 。</p>
     */
    public static void runExperiment1_2(){
        // 由于日期不便使用控制台输入，在此指定若干日期进行检测。
        LocalDate[] localDates = {
                LocalDate.of(2004, 7, 28),  // 武汉轨道交通1号线的开通时间。
                LocalDate.of(2012, 12, 28), // 武汉轨道交通2号线的开通时间。
                LocalDate.of(2015, 12, 28), // 武汉轨道交通3号线的开通时间。
                LocalDate.of(2013, 12, 28), // 武汉轨道交通4号线的开通时间。
                LocalDate.of(2021, 12, 26), // 武汉轨道交通5号线的开通时间。
                LocalDate.of(2016, 12, 28), // 武汉轨道交通6号线的开通时间。
                LocalDate.of(2018, 10, 1),  // 武汉轨道交通7号线的开通时间。
                LocalDate.of(2017, 12, 26), // 武汉轨道交通8号线的开通时间。
                LocalDate.of(2018, 10, 1),  // 武汉轨道交通11号线的开通时间。
                LocalDate.of(2021, 12, 26), // 武汉轨道交通16号线的开通时间。
                LocalDate.of(2023, 12, 20), // 武汉轨道交通19号线的开通时间。
                LocalDate.of(2017, 12, 26)  // 武汉轨道交通阳逻线的开通时间。
        };

        // 借用 LocalDates 类对 Dates 类进行初始化，从而简化整个初始化过程。
        Date[] dates = new Date[localDates.length];
        for(int i = 0; i < dates.length; i++) {
            dates[i] = Date.from(localDates[i].atStartOfDay(ZoneId.of("Asia/Shanghai")).toInstant());
        }

        Solution.sort(dates);   // 调用 Solution.sort 方法，对这些日期进行冒泡排序。
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
        String path;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter a path to go: ");
        path = sc.nextLine();
        try {
            Solution.tree(path);
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }

    }

    public static void runExperiment1_5(){
        Integer[] n = new Integer[10];
        for(int i = 0; i < n.length; i++) {
            n[i] = i;
        }
        System.out.println(Solution.toString(n));
    }

    public static void runExperiment1_6(){

    }

    public static void runExperiment1_7(){
        Map<Integer, String> map = new HashMap<>();

        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        map.put(4, "Date");
        map.put(5, "Elderberry");

        System.out.println(Solution.toString(map));
    }

    public static void runExperiment1_8() {
        try {
            Solution.copyTextFile("./test/exp1_7/text_source.txt", "./test/exp1_7/text_destination.txt");
            Solution.copyFile("./test/exp1_7/mp3_source.mp3", "./test/exp1_7/mp3_destination.mp3");
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
