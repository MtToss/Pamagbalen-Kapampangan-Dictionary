import csv

csv_file = 'src\main\python\csv\kapampangan_translations.csv'

with open(csv_file, mode='r', newline='', encoding='utf-8') as file:
    reader = csv.reader(file)
    
    next(reader)
    
    for row in reader:
        kapampangan_word, tagalog_word, english_word = row
        print(f"Kapampangan: {kapampangan_word} | Tagalog: {tagalog_word} | English: {english_word}")
