import java.util.ArrayList;
import java.lang.StringBuilder;

public class StepFasterThanRecursive
{
  public static void main(String[] args)
  {
    int[] stepList = {1,2,3};
    int n = 40;

    System.out.println(takeSteps(n, stepList));
  }

  static long takeSteps(int n, int[] stepList)
  {
    // the number of ways to climb the stairs is what we eventually want to return
    long nOfWays = 0;
    // will use the number of posible step option a bit
    int nOfStepOptions = stepList.length;

    // first want to gather the maximum posible of each step option from stepOptionList
    int[] maxOfEachStep = new int[nOfStepOptions];

    // maxStop will be used to detect once we have reached the end
    int maxStop = 0;

    // capture maxOfEachStep
    for(int i = 0; i<nOfStepOptions; i++)
    {
      maxOfEachStep[i] = calcMaxOfParStep(n,stepList[i]);
      maxStop = maxStop + (maxOfEachStep[i] * stepList[i]);
    }

    // generate posible combinations of steps of each kind of steps
    // that will get us to n
    int[] potentialCombination = {};
    ArrayList<int[]> stepCombinationList = new ArrayList<int[]>();
    while (true)
    {
      potentialCombination = genNewCombination(potentialCombination,maxOfEachStep);

      if(testStepCombination(potentialCombination,stepList)==n)
      {
        stepCombinationList.add(potentialCombination.clone());
      }
      if(testStepCombination(potentialCombination,stepList)==maxStop)
      {
        break;
      }
    }

    // add up the nOfWays :-)
    for (int i = 0; i<stepCombinationList.size();i++)
    {
      nOfWays = nOfWays + multCombination(stepCombinationList.get(i));
    }

    return nOfWays;
  }

  // fucntion to calculate the maximum number of each aprticular step
  static int calcMaxOfParStep(int n, int k)
  {
    int result = 0;
    while (n>0)
    {
      n = n-k;
      if(n>=0)
      {
        result = result + 1;
      }
    }
    return result;
  }

  // want to easily be able to view arrays
  static String showArray(int[] A)
  {
    StringBuilder builder = new StringBuilder();
    for(int i = 0;i<A.length;i++)
    {
      builder.append(A[i] + " ");
    }
    String result = builder.toString();
    return result;
  }


  static int[] genNewCombination(int[] potentialCombination,int[] maxOfEachStep)
  {
    if(potentialCombination.length == 0)
    {
      int[] tempArray = new int[maxOfEachStep.length];
      for (int i = 0;i<maxOfEachStep.length; i++)
      {
        tempArray[i] = 0;
      }
      return tempArray;
    }
    else
    {
      int i = 0;
      while(i<maxOfEachStep.length)
      {
        if(potentialCombination[i]<maxOfEachStep[i])
        {
        potentialCombination[i]=potentialCombination[i]+1;
        return potentialCombination;
        }
        else
        {
          potentialCombination[i] = 0;
          i = i + 1;
        }
      }
      return potentialCombination;
    }
  }

  static int testStepCombination(int[] potentialCombination, int[] stepList)
  {
    int result = 0;
    for(int i = 0;i<stepList.length;i++)
    {
      result = result + (potentialCombination[i] * stepList[i]);
    }
    return result;
  }

  static int combination(int n,int r)
  {
    int result = 1;
    if ((n-r)<r)
    {
      r = n-r;
    }
    for (int i = 0; i<r;i++)
    {
      result = result * (n-i);
      result = result / (i+1);
    }
    return result;
  }

  static int multCombination(int[] kBar)
  {
    int result = 1;
    int numerator = 0;
    for (int i = 0; i < kBar.length; i++)
    {
      numerator = numerator + kBar[i];
      result = result * combination(numerator, kBar[i]);
    }
    return result;
  }

}
