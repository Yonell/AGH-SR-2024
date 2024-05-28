from math import trunc

import requests
import random

class CNJokeInterface():
    URLs = None
    def __init__(self):
        URLs = [
            "https://api.chucknorris.io/jokes/random",
        ]
        pass

    def getAJoke(self) -> str:
        index = trunc(random.random()*len(self.URLs))
        x = requests.get(self.URLs[index])
        return x.json()['value']
