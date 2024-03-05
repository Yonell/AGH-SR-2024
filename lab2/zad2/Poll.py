class Poll:
    id = None
    votes = []
    possible_values = []
    def __init__(self,id,possible_values):
        self.id = id
        self.possible_values = possible_values
        print(self.id, self.possible_values)
        pass

    def addVote(self, vote):
        if vote.value not in self.possible_values:
            return -1
        self.votes.append(vote)
        return 0

    def updateVote(self, id, newValue):
        if newValue not in self.possible_values:
            return -1

        vote_index = None
        for i in range(len(self.votes)):
            if id == self.votes[i].id:
                vote_index = i

        self.votes[vote_index].value = newValue

    def removeVote(self, id):
        vote_index = None
        for i in range(len(self.votes)):
            if id == self.votes[i].id:
                vote_index = i

        if vote_index == None:
            return -1

        self.votes.pop(vote_index)
        return 0

    def updatePoll(self, newPossibleValues):
        occuringValues = []
        for vote in self.votes:
            if vote.value not in occuringValues:
                occuringValues.append(vote.value)

        for value in occuringValues:
            if value not in newPossibleValues:
                return -1

        self.possible_values = newPossibleValues

        return 0

    def getVotes(self):
        return self.votes

    def getResults(self):
        results = {
            value: 0
            for value in self.possible_values
        }
        for vote in self.votes:
            results[vote.value] += 1

        return results


