import pandas as pd
import numpy as np
import sys

first_arg = sys.argv[1]

print(pd.__file__)
print(np.__file__)

def sorting(genre):
	df = pd.read_csv('ratings.csv', usecols=['userId','movieId','rating'])

	movie_titles = pd.read_csv('movies.csv', usecols=['movieId','title','genres'])
	imdb_to_id = pd.read_csv('links.csv', usecols=['movieId','imdbId'])

	ratings = pd.DataFrame(df.groupby('movieId')['rating'].mean())
	ratings['numRatings'] = pd.DataFrame(df.groupby('movieId')['rating'].count())

	print(ratings.head())
	movie_titles = pd.merge(movie_titles,imdb_to_id, left_on = 'movieId', right_on = 'movieId')
	movie_titles = movie_titles[movie_titles['genres'].str.contains(genre)]

	movie_titles = movie_titles.join(ratings['numRatings'], on = 'movieId')
	movie_titles = movie_titles.join(ratings['rating'], on = 'movieId')
	print(movie_titles.head())

	new = movie_titles[movie_titles['numRatings'] > 50].sort_values(by='rating', ascending=False).head(20)
	print(new.head())

	file = open("sorted.txt","w")
	for index,row in new.iterrows():
		file.write(str(row['imdbId']))
		file.write("\n")
	file.close()

	return

print(sorting(first_arg))
