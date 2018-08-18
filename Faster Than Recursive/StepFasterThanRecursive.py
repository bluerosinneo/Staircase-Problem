# Step problem

def whilePositive(steps,k):
    n = 0
    while (steps>0):
        steps=steps-k
        if(steps>=0):
            n = n + 1
    return n

def isStepCombinationGood(steps,stepOptionList, itrTracker):
    if(sum([x*y for x,y in zip(itrTracker['lastCombination'],stepOptionList)])==steps):
        return True
    else:
        if(sum([x*y for x,y in zip(itrTracker['lastCombination'],stepOptionList)])==itrTracker['max']):
            itrTracker['done']=True
        return False


def genStepCombination(steps, stepOptionList, numPosSubs, itrTracker):
    combination = []
    if(len(itrTracker['lastCombination'])==0):
        for i in range(0,len(stepOptionList)):
            combination.append(0)
        itrTracker['lastCombination']=combination
        return isStepCombinationGood(steps,stepOptionList, itrTracker)

    increased = False
    i = 0
    while (itrTracker['done']==False & i<len(stepOptionList)):
        if(itrTracker['lastCombination'][i]<numPosSubs[i]):
            itrTracker['lastCombination'][i] = itrTracker['lastCombination'][i]+1
            increased = True
            return isStepCombinationGood(steps,stepOptionList, itrTracker)
        else:
            itrTracker['lastCombination'][i] = 0
            i = i + 1

def combination(n,r):
    if((n-r)<r):
        r=n-r
    result = 1
    for i in range(0,r):
        result=result*(n-i)
        result=result/(i+1)
    return result

def multCombination(kBar):
    result = 1
    numerator = 0
    for i in range(0,len(kBar)):
        numerator = numerator + kBar[i]
        result = result*combination(numerator,kBar[i])
    return result

def calcSteps(steps, stepOptionList):
    stepCombinationList = []
    numPosSubs = []
    nStepOption = len(stepOptionList)

    for i in range(0,nStepOption):
        numPosSubs.append(whilePositive(steps,stepOptionList[i]))

    itrTracker = {'nStepOptions': nStepOption,
     'lastCombination': [], 'max': sum([x*y for x,y in zip(stepOptionList,numPosSubs)]),  'done': False}
    while (itrTracker['done']==False):
        if(genStepCombination(steps, stepOptionList, numPosSubs, itrTracker)==True):
            stepCombinationList.append(list(itrTracker['lastCombination']))

    result = 0
    for i in range(0,len(stepCombinationList)):
        result = result + multCombination(stepCombinationList[i])
    return int(result)




stepOptionList = [1,2,3]
steps =40

print(calcSteps(steps,stepOptionList))
