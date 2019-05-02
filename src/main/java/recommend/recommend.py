import pandas as pd
import numpy as np
import warnings
import sys
warnings.filterwarnings('ignore')

first_arg = sys.argv[1]
second_arg = sys.argv[2]

# input list of imdbIds and ratings as two arrays

def recommend(first_arg,second_arg) :

	imdbIds = first_arg.split(", ")
	imdbRatings = second_arg.split(", ")

	output = {}
	df = pd.read_csv('src/main/java/recommend/ratings.csv', usecols=['userId','movieId','rating'])

	movie_titles = pd.read_csv('src/main/java/recommend/movies.csv', usecols=['movieId','title'])
	imdb_to_id = pd.read_csv('src/main/java/recommend/links.csv', usecols=['movieId','imdbId'])

	ratings = pd.DataFrame(df.groupby('movieId')['rating'].mean())
	ratings['numRatings'] = pd.DataFrame(df.groupby('movieId')['rating'].count())

	movie_matrix = df.pivot_table(index='userId', columns='movieId', values='rating')

	for i in range(len(imdbIds)):
		id = imdb_to_id.loc[imdb_to_id.loc[:,'imdbId'] == int(imdbIds[i]),:]['movieId'].values[0]
		movie = movie_matrix[id] ## the rating correlations between this movie and the other movies

		similar_to_movie = movie_matrix.corrwith(movie)

		corr_movie = pd.DataFrame(similar_to_movie, columns=['correlation'])
		corr_movie.dropna(inplace=True)
		corr_movie = corr_movie.join(ratings['numRatings'])

		#corr_movie.join(imdb_to_id, on = 'movieId', how = 'left')
		corr_movie = pd.merge(corr_movie,imdb_to_id, left_on = 'movieId', right_on = 'movieId')

		if (int(imdbRatings[i]) > 5):
		  	new = corr_movie[corr_movie['numRatings'] > 50].sort_values(by='correlation', ascending=False).head(21)
		else:
			new = corr_movie[corr_movie['numRatings'] > 50].sort_values(by='correlation', ascending=True).head(21)

		for index,row in new.iterrows():
			if int(row['imdbId']) in output.keys():
				output[int(row['imdbId'])] += int(imdbRatings[i])*abs(row['correlation'])
			else:
				output[int(row['imdbId'])] = int(imdbRatings[i])*abs(row['correlation'])

	recs = sorted(output, key=output.get, reverse=True)[:21]
	for j in range(1,len(recs)):
		print(str(recs[j]))
	return
recommend(first_arg, second_arg)
#recommend(["114709"],["10"])
