package resourses;

public class ResourcesPath {

    public static final String bigFilePath = "/Users/oleksii/Documents/Java/ForkJoinTest/ForkJoin/src/resourses/big.txt";

    public static final String whatIsLoremIpsumName = "What is Lorem Ipsum.txt";
    public static final String whyUseLoremIpsumName = "What is Lorem Ipsum.txt";
    public static final String whatIsLoremIpsum = "What is Lorem Ipsum.txt";

    public static String loremIpsumPath(String fileName) {

        return "/Users/oleksii/Documents/Java/ForkJoinTest/ForkJoin/src/resourses/" + fileName;
    }
}
