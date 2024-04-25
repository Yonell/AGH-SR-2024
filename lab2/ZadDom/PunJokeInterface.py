from math import trunc

import requests
import random

class PunJokeInterface():
    URLs = None
    def __init__(self):
        URLs = [
            "https://v2.jokeapi.dev/joke/Pun?blacklistFlags=nsfw,religious,political,racist,sexist,explicit&format=txt"
        ]
        pass

    def getAJoke(self) -> str:
        index = trunc(random.random()*len(self.URLs))
        x = requests.get(self.URLs[index])
        return x.text
