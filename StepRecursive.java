// This code follows the following post.
// https://cramerexplainsmath.com/2018/06/18/number-of-ways-to-climb-a-flight-of-stairs-when-taking-one-two-three-steps-at-a-time-java-recursive/
// @desc Solving How many ways can one climb a set of stairs of any length
// by taking step options from any number of options?
// This is the recursive solution
// @author Cramer Grimes cramergrimes@gmail.com

public class StepRecursive
{
  public static void main(String[] args)
  {
    int[] stepList = {1,2,3};
    int n = 40;
    System.out.println(takeSteps(n,stepList));
  }

  static long takeSteps(int n, int[] stepList)
  {
    long result = 0;
    for(int i = 0; i < stepList.length; i++)
    {
      if(n-stepList[i] > 0)
      {
        result = result + takeSteps(n-stepList[i],stepList);
      }
      else if(n-stepList[i]==0)
      {
        result = result + 1;
      }
    }
    return result;
  }
}
