/**
 * <h3>wsd-project</h3>
 * <p></p>
 *
 * @author : 王松迪
 * 2024-03-14 10:53
 **/
public class Test {

    public static void main(String[] args) {

        testmatcher();
    }

     public static class A{
        private Integer a;


        public A(){

        }
        public A(int a) {
            this.a = a;
        }

     }

     private void test() {
         int[] timeFactor = {0, 1, 24, 30 * 24, 365 * 24};
         int[][] timeScope = {{100}, {0,1,0,0,4}, {0,30}, {0,40}, {0,70}};

         int s = 36500 * 24;

         int index = -1;
         int factor = -1;
         for (int i = 0; i < timeFactor.length; i++) {
             if(s >= timeFactor[i]) {
                 index = i;
                 factor = timeFactor[i];
             }
         }


         int[] scope = timeScope[index];
         int i = factor == 0 ? 0 : s / factor;
         int max = Math.min(i, scope.length - 1);
         System.out.println(scope[max]);
     }

    private static final String REPLACEMENT = "*";
     private static  void testmatcher() {
        String input = "sdfsdfdsccv&*&*&*&*.....())()()(8923748797JHKL";


         System.out.println(input.replaceAll("\\.", REPLACEMENT));
     }
}
