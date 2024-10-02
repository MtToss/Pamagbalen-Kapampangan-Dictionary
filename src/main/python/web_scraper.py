import requests
from bs4 import BeautifulSoup

def scrape_translations(url):
    headers = {
        'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36'
    }

    response = requests.get(url, headers=headers)

    if response.status_code == 200:
        soup = BeautifulSoup(response.text, 'html.parser')
        rows = soup.find_all('tr')
        translations = {}

        for row in rows:
            columns = row.find_all('td')
            if len(columns) == 2:
                kapampangan_word = columns[0].text.strip()
                target_word = columns[1].text.strip()
                translations[kapampangan_word] = target_word
        
        return translations
    else:
        print(f"FAILED to retrieve from {url}. Status code: {response.status_code}")
        return {}

url1 = 'https://www.tagaloglang.com/kapampangan-to-tagalog/'
url2 = 'https://www.tagaloglang.com/kapampangan-to-english/'

translations_tagalog = scrape_translations(url1)
translations_english = scrape_translations(url2)

print("\nKapampangan Translations:")
for kapampangan_word, tagalog_word in translations_tagalog.items():
    english_word = translations_english.get(kapampangan_word, '')  # Get English translation or empty string
    print(f"Kapampangan: {kapampangan_word} | Tagalog: {tagalog_word} | English: {english_word}")
