import fastapi
import requests
from fastapi.responses import HTMLResponse
from fastapi import status

api = fastapi.FastAPI()

currencies = ['aed', 'afn', 'all', 'amd', 'ang', 'aoa', 'ars', 'aud', 'awg', 'azn', 'bam', 'bbd', 'bdt', 'bgn', 'bhd', 'bif', 'bmd', 'bnd', 'bob', 'brl', 'bsd', 'btn', 'bwp', 'byn', 'bzd', 'cad', 'cdf', 'chf', 'clp', 'cny', 'cop', 'crc', 'cup', 'cve', 'czk', 'djf', 'dkk', 'dop', 'dzd', 'egp', 'ern', 'etb', 'eur', 'fjd', 'fkp', 'fok', 'gbp', 'gel', 'ggp', 'ghs', 'gip', 'gmd', 'gnf', 'gtq', 'gyd', 'hkd', 'hnl', 'hrk', 'htg', 'huf', 'idr', 'ils', 'imp', 'inr', 'iqd', 'irr', 'isk', 'jep', 'jmd', 'jod', 'jpy', 'kes', 'kgs', 'khr', 'kid', 'kmf', 'kpw', 'krw', 'kwd', 'kyd', 'kzt', 'lak', 'lbp', 'lkr', 'lrd', 'lsl', 'lyd', 'mad', 'mdl', 'mga', 'mkd', 'mmk', 'mnt', 'mop', 'mru', 'mur', 'mvr', 'mwk', 'mxn', 'myr', 'mzn', 'nad', 'ngn', 'nio', 'nok', 'npr', 'nzd', 'omr', 'pab', 'pen', 'pgk', 'php', 'pkr', 'pln', 'pyg', 'qar', 'ron', 'rsd', 'rub', 'rwf', 'sar', 'sbd', 'scr', 'sdg', 'sek', 'sgd', 'shp', 'sll', 'sos', 'srd', 'ssp', 'stn', 'syp', 'szl', 'thb', 'tjs', 'tmt', 'tnd', 'top', 'try', 'ttd', 'tvd', 'twd', 'tzs', 'uah', 'ugx', 'usd', 'uyu', 'uzs', 'ves', 'vnd', 'vuv', 'wst', 'xaf', 'xcd', 'xdr', 'xof', 'xpf', 'yer', 'zar', 'zmw']

@api.get("/")
async def root() -> HTMLResponse:
    with open("./root.html", 'r') as f:
        return HTMLResponse(f.read(), status_code=status.HTTP_200_OK)

@api.get("/exchange/")
def coin_statistics(currency: str) -> HTMLResponse:

    currency = currency.lower()

    if currency not in currencies:
        return HTMLResponse(status_code=status.HTTP_400_BAD_REQUEST, content="Unknown currency")

    values = []

    if currency == "pln":
        values.append(1)
    else:
        try:
            x = requests.get("http://api.nbp.pl/api/exchangerates/rates/a/" + currency + "/?format=json").json()["rates"][0]["mid"]
            values.append(x)

            request = requests.get("https://cdn.jsdelivr.net/npm/@fawazahmed0/currency-api@latest/v1/currencies/eur.json")
            x = request.json()["eur"][currency] if currency != "eur" else 1
            x = x**-1
            x *= request.json()["eur"]["pln"]
            values.append(x)

            request = requests.get("https://api.frankfurter.app/latest")
            x = request.json()["rates"][currency.upper()] if currency != "eur" else 1
            x = x**-1
            x *= request.json()["rates"]["pln".upper()]
            values.append(x)

        except:
            return_val = "<a href=\"http://127.0.0.1:8000/\">back</a><br>Communication error with external services"

            return HTMLResponse(status_code=status.HTTP_500_INTERNAL_SERVER_ERROR, content = return_val)




    mean = sum(values)/len(values)
    sd = sum([(values[i]-mean)**2  for i in range(len(values))])/(len(values) - 1) if len(values) > 1 else 0
    return_val = "<a href=\"http://127.0.0.1:8000/\">back</a><br>" + "STATISTICS FOR " + currency + "<br><br>"\
        "mean: " + str(mean) + " PLN <br>" +\
        "standard deviation: " + str(sd) + " PLN"
    return HTMLResponse(status_code=status.HTTP_200_OK, content=return_val)


@api.get("/currencies/")
async def available_currencies() -> HTMLResponse:
    return_val = "<a href=\"http://127.0.0.1:8000/\">back</a><br>" + "AVAILABLE CURRENCIES: <br><br>"
    for i in currencies:
        return_val += i + "<br>"

    return HTMLResponse(status_code=200, content=return_val)