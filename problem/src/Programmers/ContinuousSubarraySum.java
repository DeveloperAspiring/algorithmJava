package Programmers;

public class ContinuousSubarraySum {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        solution(new int[] {2, 2, 2, 2, 2},6 );
    }

    public static int[] solution(int[] sequence, int k) {


        int size = sequence.length;

        int total = 0;
        int len = 1000000;
        int end =  size -1;
        int[] answer = {size-1,size-1};
        for(int i = size -1; i >= 0 ; i--) {
            total = total + sequence[i];

            if(total > k) {
                total -= sequence[end--];
            }


            if(total == k) {
                if(len> end - i) {
                    len = end - i;
                    answer[0] = i;
                    answer[1] = end;
                }
                else if (len == end -i) {
                    if(answer.length > 1 && answer[0] > i) {
                        answer[0] = i;
                        answer[1] = end;
                    }
                }
            }



        }
        //System.out.println(answer[0] +" "+ answer[1]);
        return answer;
    }
}
