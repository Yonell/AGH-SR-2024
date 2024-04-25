import torch
from transformers import pipeline
from CNJokeInterface import CNJokeInterface
from ProgrammingJokeInterface import ProgrammingJokeInterface
from DarkJokeInterface import DarkJokeInterface
from PunJokeInterface import PunJokeInterface
from SpookyJokeInterface import SpookyJokeInterface
from XMasJokeInterface import XMasJokeInterface
from DadJokeInterface import DadJokeInterface




class GeminiJokeProvider:
    pipe = None
    SYSTEM_MESSAGE = """Your task is to assign a number to joke topics. You will receive themes in user statements. If a theme does not fit any from the standard list of topics, you are to create a joke on that theme yourself. You must not add new topics to the standard list. In response, you should generate either the topic number or the entire joke. If it's a topic number, you should reply with only that number, without any description. If a theme can be described by one of the standard topics, you must not tell your joke. You must not add your own topics.
    Do not add ANYTHING to just a number NOR the joke.
    Here's the list of standard topics:
    
    0. Chuck Norris Jokes: Humorous anecdotes that exaggerate the toughness, strength, and invincibility of Chuck Norris. These jokes often portray him as an over-the-top, mythical figure capable of superhuman feats, contributing to a playful and entertaining narrative.
    1. Programming or Geek Jokes: Jokes centered around programming, coding, and geek culture. These may include witty references, clever wordplay, and humorous situations related to technology, software development, computer science, and popular geeky topics like sci-fi, fantasy, or gaming.
    2. Dark Humor: A form of humor that addresses morbid, taboo, or sensitive subjects in a way that may be unsettling to some. Dark humor often relies on irony, sarcasm, and unexpected twists to bring levity to serious or uncomfortable topics, pushing the boundaries of conventional comedic themes.
    3. Wordplay: Jokes that revolve around clever manipulation of language, words, or phrases. These jokes often employ double entendre, puns, linguistic nuances, or play on words to create humorous and witty situations, requiring a certain level of linguistic awareness to fully appreciate.
    4. Ghosts, Vampires, Zombies, and the like: Jokes that explore supernatural, horror, or mythical themes, featuring ghosts, vampires, zombies, and other fantastical creatures. These jokes often blend spooky elements with humor, creating scenarios that playfully incorporate paranormal or otherworldly elements.
    5. Christmas Jokes: Humorous content specifically related to the Christmas season. These jokes may cover various aspects of holiday traditions, festive celebrations, gift-giving, Santa Claus, winter weather, and other Christmas-related themes, spreading joy and laughter during the holiday season.
    6. Dad Jokes: Jokes characterized by their simplicity, puns, and stereotypical dad humor. Dad jokes often rely on corny and straightforward humor, with a delivery style associated with the stereotypical dad figure. These jokes are known for their cheesy charm and may elicit groans or laughter due to their light-hearted and wholesome nature.
    """
    URLs = [
        CNJokeInterface(),
        ProgrammingJokeInterface(),
        DarkJokeInterface(),
        PunJokeInterface(),
        SpookyJokeInterface(),
        XMasJokeInterface(),
        DadJokeInterface()
    ]
    def __init__(self):
        self.pipe = pipeline("text-generation",
                model="cognitivecomputations/dolphin-2.0-mistral-7b",
                torch_dtype=torch.bfloat16,
                device_map="auto",
                trust_remote_code=True,

                )

    def getAJokeAbout(self, input: str) -> str:
        message = f"""<|im_start|>system
        {self.SYSTEM_MESSAGE}<|im_end|>
        <|im_start|>user
        {input}<|im_end|>
        <|im_start|>assistant
        """

        #prompt = self.pipe.tokenizer.tokenize(message, return_tensors='pt')
        outputs = self.pipe(message, max_new_tokens=100, do_sample=True, temperature=0.7, top_k=50, top_p=0.95)
        return outputs[0]["generated_text"]
