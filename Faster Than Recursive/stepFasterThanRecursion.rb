# hopefully I can do this

# a function for showing arrays of arrays nicer
# don't like that cap variables are constent :-(
def show_array(a_bar)
  a_bar.each do |a|
    print a
    print "\n"
  end
end

# gives the number os steps a potential step combination would yield
def step_combination_length(potential_combination, step_list)
  result = 0
  if (potential_combination.length == 0)
    return 0
  end
  (0..(step_list.length-1)).each do |i|
    result = result + potential_combination[i] * step_list[i]
  end
  return result
end

# generates new potential step combinations by
# treating each step combination like the indeci of a change_Numbers
# using the max_of_each_step as the base of that number
def generate_new_step_combination(last_potential_combination, max_of_each_step)
  if (last_potential_combination.length == 0)
    last_potential_combination = Array.new(max_of_each_step.length, 0)
  else
    (0..(max_of_each_step.length-1)).each do |i|
      if (last_potential_combination[i]<max_of_each_step[i])
        last_potential_combination[i] = last_potential_combination[i] + 1
        return last_potential_combination
      elsif (last_potential_combination[i]==max_of_each_step[i])
        last_potential_combination[i] = 0
      end
    end
  end
  return last_potential_combination
end

def n_combination(m, k)
  result = 1
  counter = 0
  if ((m-k)<k)
    k = m-k
  end
  while (counter < (k))
    result = result * (m-counter)
    result = result / (1 + counter)
    counter = counter + 1
  end
  return result
end

def n_multi_combination(n_bar)
  running_n = 0
  result = 1
  for cur_n in n_bar
    running_n = running_n +cur_n
    result = result * n_combination(running_n,cur_n)
  end
  return result
end

n = 40
step_list = [1,2,3]

# generate max_of_each_step
max_of_each_step = []
for step in step_list
  max_of_each_step.push(n/step)
end
# i'm fairly certin that int/int -> gives int

# creating step_combination_list
step_combination_list = []
potential_combination = []
stop_max = step_combination_length(max_of_each_step,step_list)
while (step_combination_length(potential_combination,step_list) != stop_max)
  potential_combination = generate_new_step_combination(potential_combination, max_of_each_step)
  if (step_combination_length(potential_combination,step_list)==n)
    step_combination_list.push(Array.new(potential_combination)) # make sure we do a copy
  end
end

# sum up combination result
n_ways = 0
for combination in step_combination_list
  n_ways = n_ways + n_multi_combination(combination)
end

puts n_ways
