from numpy import exp, array, random, dot 

class NeuralNetwork():
    def __init__(self):
        # seed random so same numbers generated everytime
        random.seed(1)

        # model neuron, 3 input, 1 output
        # assign random weights to 3x1 matrix, range -1 to 1
        self.synaptic_weights = 2 * random.random((3,1)) - 1


    # Sigmoid function, S shaped curve
    # pass the weighted sum of the inputs to normalise 
    # between 0 and 1
    def __sigmoid(self, x):
        return 1 / (1 + exp(-x))


    # Sigmoid derivative
    # This is the gradient of the curve
    # Indicates confidence in current weight.
    def __sigmoid_derivative(self, x):
        return x * (1 - x)

    
    # Train the network through trial and error
    # Adjust the synaptic weights each time
    def train(self, training_set_inputs, training_set_outputs, number_of_training_iterations):
        for iteration in range(number_of_training_iterations):
            # Pass the training set through the network (a single neuron)
            output = self.think(training_set_inputs)


            # Calculate error
            error = training_set_outputs - output

            # Multiple error by input and gradient of Sigmoid curve 
            # Less confident weights are adjusted more
            # 0 inputs do not change the weights
            adjustment = dot(training_set_inputs.T, error * self.__sigmoid_derivative(output))

            # Adjust weights
            self.synaptic_weights += adjustment

    def think(self, inputs):
        # Pass inputs through the neuron
        return self.__sigmoid(dot(inputs, self.synaptic_weights))

if __name__ == "__main__":

    # Initialise single neuron
    neural_network = NeuralNetwork()

    print("random starting synaptic weights: ")
    print(neural_network.synaptic_weights)

    # Training set, 4 examples of 3 input values and 1 output value
    training_inputs = array([[0,0,1],[1,1,1],[1,0,1],[0,1,1]])
    training_outputs = array([[0,1,1,0]]).T


    # Train the network over 10000 iterations
    # *Training montage here*
    neural_network.train(training_inputs, training_outputs, 10000)

    print("New synaptic weights after training: ")
    print(neural_network.synaptic_weights)

    # Test network with new input
    print("Considering new situation [1, 0, 0] -> ?: ")
    print(neural_network.think(array([1,0,0])))
