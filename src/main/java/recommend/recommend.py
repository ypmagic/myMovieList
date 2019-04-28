import pandas as pd
import numpy as np
import warnings
warnings.filterwarnings('ignore')

print(pd.__file__)
print(np.__file__)

# input list of imdbIds and ratings as two arrays

def recommend(imdbIds,imdbRatings) :
	df = pd.read_csv('ratings.csv', usecols=['userId','movieId','rating'])

	movie_titles = pd.read_csv('movies.csv', usecols=['movieId','title'])
	imdb_to_id = pd.read_csv('links.csv', usecols=['movieId','imdbId'])

	ratings = pd.DataFrame(df.groupby('movieId')['rating'].mean())
	ratings['numRatings'] = pd.DataFrame(df.groupby('movieId')['rating'].count())

	movie_matrix = df.pivot_table(index='userId', columns='movieId', values='rating')

	print(imdbIds[0])
	for i in range(len(imdbIds)):
		id = imdb_to_id.loc[imdb_to_id.loc[:,'imdbId'] == imdbIds[i],:]['movieId'].values[0]
		print(id)
		movie = movie_matrix[id] ## the rating correlations between this movie and the other movies

		similar_to_movie = movie_matrix.corrwith(movie)

		corr_movie = pd.DataFrame(similar_to_movie, columns=['correlation'])
		corr_movie.dropna(inplace=True)
		corr_movie = corr_movie.join(ratings['numRatings'])

		if (imdbRatings[i] > 5):
			print(corr_movie[corr_movie['numRatings'] > 50].sort_values(by='correlation', ascending=False).head(11))
		else:
			print(corr_movie[corr_movie['numRatings'] > 50].sort_values(by='correlation', ascending=True).head(11))

def testing(imdbIds):
	return imdbIds

recommend([114709],[10])
