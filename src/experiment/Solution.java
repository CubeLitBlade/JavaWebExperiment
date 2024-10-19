package experiment;

import java.io.*;
import java.util.*;
import java.util.logging.Logger;

public class Solution {
    /**
     * 将 {@code int} 型数组通过冒泡排序法按升序排序。
     * @param arr 待排序的 {@code int} 型数组。
     */
    public static void sort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] > arr[j]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 将 {@code java.util.Date} 型数组通过冒泡排序法按升序排序。
     * @param arr 待排序的 {@code java.util.Date} 型数组。
     */
    public static void sort(Date[] arr)
    {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    Date temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }

    /**
     * 通过二分查找查找顺序 {@code int} 型数组中的元素。
     * @param arr 待查找的 {@code int} 型数组。
     * @param key 待查找的 {@code int} 型元素。
     * @return 如果在数组 {@code arr} 中存在 {@code key}，则返回所找到的其中一个 {@code key} 的索引；否则返回 {@code -1}。
     */
    public static int binarySearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] < key) {
                left = mid + 1;
            }
            if (arr[mid] > key) {
                right = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 使用链表解决约瑟夫问题：
     * <p>假设有 {@code n} 个人围成一个环，每隔 {@code k} 个人，则命中的人必须自杀，求解最终的幸存者的编号。</p>
     * @param n 约瑟夫环中待自杀的总人数。
     * @param k 约瑟夫环中每次自杀的间隔数。
     * @return 如果 {@code n > 0}，则返回幸存者的编号 (从 {@code 1} 开始)；否则，返回 {@code -1}。
     */
    public static int JosephusCircle(int n, int k) {
        Logger logger = Logger.getLogger(Solution.class.getName());
        if(n > 0) {
            LinkedList<Integer> victimList = new LinkedList<>();
            for (int i = 1; i <= n; i++) {
                victimList.add(i);
            }

            int victim = 0;
            while (victimList.size() > 1) {
                victim = (victim + k - 1) % victimList.size();

                // 不想污染正常的输出流，总之把它丢到了调试信息里面。
                // 或许返回一个数组也是个好主意。
                logger.info(
                        victimList.size()
                        + " little players left, trapped by fate. Player "
                        + victimList.remove(victim)
                        + " met their end. And then there "
                        + (victimList.size() == 1 ? "was " : "were ")       // 搞了半天只是为了打印正确的 was 或者 were（目移）。
                        + victimList.size()
                        + ". "
                );
            }
            return victimList.getFirst();
        }
        else {
            return -1;
        }
    }

    /**
     * 以树状结构打印目标目录及其子目录下的所有文件名。
     * @param path 待打印的目标目录。
     * @exception FileNotFoundException 如果 {@code path} 所指示的路径不存在。
     */
    public static void tree(String path) throws FileNotFoundException {
        File file = new File(path);
        if(!file.exists()){
            throw new FileNotFoundException("'" + path + "': No such file or directory. ");
        }
        else {
            __tree(path, 0);    // 如此做法是为了将 depth 封装起来。此参数与用户层无关。
        }
    }

    /**
     * 内部方法。用于递归访问目标目录及其子目录，并将其以树状结构打印出来。
     * @param path 待打印的目标目录。
     * @param depth 递归的深度。调用 {@code tree(path)} 相当于调用 {@code __tree(path, 0)}。
     */
    private static void __tree(String path, int depth)
    {
        File file = new File(path);
        for(String item : Objects.requireNonNull(file.list())){
            for (int i = 0; i < depth; i++) {
                // 根据递归深度打印空格，完成缩进格式。
                // 本来想复刻软子的那个 tree 命令的格式的，结果因为懒（无奈）。
                System.out.print("  ");
            }
            // 顺便检测一下此项目是不是目录，如果是的话就递归一层。
            File tmp = new File(path+"\\"+item);

            System.out.printf("%s    %s%n", item, tmp.isDirectory() ? "<DIR>" : "<FILE>");
            if(tmp.isDirectory()){
                __tree(path+"\\" + item, depth + 1);
            }
        }
    }

    /**
     * 遍历数组，并用字符串形式表示其元素。
     * @param arr 待遍历的数组。
     * @return {@code arr} 的 {@code String} 形式的表示。
     * @param <T> 数组中元素的类型，必须具有合适的 {@code toString} 方法的重载。
     */
    public static <T> String toString(T[] arr){
        StringBuilder sb = new StringBuilder("[");
        for(int i = 0; i < arr.length; i++){
            sb.append(arr[i]).append(i != arr.length - 1 ? ", " : "]");    // 判断是否是最后一个元素，选择加逗号还是加方括号。起到一个美观的作用。
        }
        return sb.toString();
    }

    /**
     * 遍历 {@code java.utils.List}，并用字符串形式表示其元素。
     * @param list 待遍历的 {@code java.utils.List} 。
     * @return {@code list} 的 {@code String} 形式的表示。
     * @param <T> {@code java.utils.List}中元素的类型，必须具有合适的 {@code toString} 方法的重载。
     */
    public static <T> String toString(List<T> list){
        StringBuilder sb = new StringBuilder("[");
        Iterator<T> it = list.iterator();
        while(it.hasNext()){
            sb.append(it.next()).append(it.hasNext() ? ", " : "]");     // 迭代器可以直接用 hasNext 方法判断最后一个元素。
        }
        return sb.toString();
    }

    /**
     * 遍历 {@code java.utils.Map}，并用字符串形式表示其键值对。
     * @param map 待遍历的 {@code java.utils.Map} 。
     * @return {@code map} 中键值对的 {@code String} 形式的表示。
     * @param <K> {@code java.utils.Map}中键的类型，必须具有合适的 {@code toString} 方法的重载。
     * @param <V> {@code java.utils.Map}中值的类型，必须具有合适的 {@code toString} 方法的重载。
     */
    public static <K, V> String toString(Map<K, V> map){
        StringBuilder sb = new StringBuilder("[");
        for(Map.Entry<K, V> entry : map.entrySet()){
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append(", ");
        }

        // 用增强 for 循环无法检测是否遍历到最后一个键值对。只能用这样的丑办法作为输出格式的 workaround 。
        if(sb.length() > 2)
        {
            sb.delete(sb.length() - 2, sb.length());
        }
        sb.append("]");
        return sb.toString();
    }

    /**
     * {@code copyFile} 和 {@code copyTextFile} 的先决条件，用于检查复制操作的可行性，并且返回指示源文件和目标文件的 {@code java.io.File} 对象数组。
     * @param source 指示源文件的路径，此路径必须存在且不能是目录。
     * @param destination 指示目标路径，在父目录不存在时会尝试创建。如果目标路径指示一个目录，则构造指示目标路径的 {@code java.io.File} 时，自动添加与 {@code source} 一致的文件名。
     * @return {@code java.io.File} 类型的数组 {@code files}。其中 {@code files[0]} 指示源文件路径，{@code files[1]} 指示目标文件路径。
     * @throws IOException 如果 {@code source} 指示的路径不存在，或者 {@code dest} 指示的路径不存在且无法被创建。
     * @throws IllegalArgumentException 如果 {@code destination} 指示的路径和 {@code source} 相同时或仅指示到其父目录，或者指定的 {@code source} 是一个目录。
     */
    private static File[] __copyFileValidator(String source, String destination) throws IOException, IllegalArgumentException {
        File src = new File(source);
        File dest = new File(destination);
        if (dest.isDirectory()) {
            // 当 dest 仅仅指示文件夹时，将其添加一个文件名，用来避免一些马大哈（比如我）犯错。
            dest = new File((destination + File.separator + src.getName()));
        }
        if(dest.getAbsolutePath().equals(src.getAbsolutePath())) {
            throw new IllegalArgumentException("'" + src.getAbsolutePath() + "': Already exists. ");
        }
        if (!src.exists()) {
            throw new IOException("'" + src.getAbsolutePath() + "': No such file or directory. ");
        }
        if (src.isDirectory()) {
            throw new IllegalArgumentException("'" + src.getAbsolutePath() + "': Is a directory. ");
        }
        if (dest.getParentFile().mkdirs() && !dest.exists()) {
            throw new IOException("'" + dest.getAbsolutePath() + "': Failed to create directory. ");
        }
        return new File[]{src, dest};
    }

    /**
     * 利用 {@code java.io.BufferedInputStream} 和 {@code java.io.BufferedOutputStream} 复制文件。
     * @param source 源文件的路径。
     * @param destination 目标文件的路径。
     * @throws IOException 如果复制过程异常终止。
     */
    public static void copyFile(String source, String destination) throws IOException {
        // 使用经过 __copyFileValidator 检测并纠正的对象，其中 files[0] 指示 source， files[1] 指示 destination。
        File[] files = __copyFileValidator(source, destination);

        try (
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(files[0]));
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(files[1]))
        ){
            byte[] buf = new byte[1024];
            int len;
            while ((len = bis.read(buf)) > 0) {
                bos.write(buf, 0, len);
            }
        }
        catch (IOException e) {
            throw new IOException("Failed to copy '" + source + "' to '" + destination + "'. ", e);
        }
    }

    /**
     * 利用 {@code java.io.BufferedReader} 和 {@code java.io.BufferedWriter} 复制文本文件。
     * @param source 指示源文件的路径。
     * @param destination 指示目标文件的路径。
     * @throws IOException 如果复制过程异常终止。
     */
    public static void copyTextFile(String source, String destination) throws IOException {
        // 使用经过 __copyFileValidator 检测并纠正的对象，其中 files[0] 指示 source， files[1] 指示 destination。
        File[] files = __copyFileValidator(source, destination);

        try (
                BufferedReader br = new BufferedReader(new FileReader(files[0]));
                BufferedWriter bw = new BufferedWriter(new FileWriter(files[1]))
        ){
            String line;
            while ((line = br.readLine()) != null) {
                bw.write(line);
                bw.newLine();
            }
        }
        catch (IOException e) {
            throw new IOException("Failed to copy '" + source + "' to '" + destination + "'. ", e);
        }
    }
}
