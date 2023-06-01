package resourses;

public class ResourcesPath {

    public static final String bigFilePath = "/Users/oleksii/Documents/Java/ForkJoinTest/ForkJoin/src/resourses/big.txt";

    public static final String whatIsLoremIpsumName = "What is Lorem Ipsum.txt";
    public static final String whyUseLoremIpsumName = "Why do we use Lorem Ipsum.txt";
    public static final String loremIpsumName = "Lorem ipsum.txt";

    public static String loremIpsumPath(String fileName) {

        return "/Users/oleksii/Documents/Java/ForkJoinTest/ForkJoin/src/resourses/" + fileName;
    }
}
