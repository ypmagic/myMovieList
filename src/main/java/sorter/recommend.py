import pandas as pd 
import numpy as np
import warnings
warnings.filterwarnings('ignore')
import matplotlib.pyplot as plt

def recommend(imdbIds,imdbRatings, num) :
	output = {}
	df = pd.read_csv('ratings.csv', usecols=['userId','movieId','rating'])

	movie_titles = pd.read_csv('movies.csv', usecols=['movieId','title','genre'])
	imdb_to_id = pd.read_csv('links.csv', usecols=['movieId','imdbId'])

	ratings = pd.DataFrame(df.groupby('movieId')['rating'].mean())
	ratings['numRatings'] = pd.DataFrame(df.groupby('movieId')['rating'].count())

	movie_matrix = df.pivot_table(index='userId', columns='movieId', values='rating')

	for i in range(len(imdbIds)): 
		id = imdb_to_id.loc[imdb_to_id.loc[:,'imdbId'] == imdbIds[i],:]['movieId'].values[0]
		movie = movie_matrix[id] ## the rating correlations between this movie and the other movies 

		similar_to_movie = movie_matrix.corrwith(movie)

		corr_movie = pd.DataFrame(similar_to_movie, columns=['correlation'])
		corr_movie.dropna(inplace=True)
		corr_movie = corr_movie.join(ratings['numRatings'])

		#corr_movie.join(imdb_to_id, on = 'movieId', how = 'left')
		corr_movie = pd.merge(corr_movie,imdb_to_id, left_on = 'movieId', right_on = 'movieId')

		if (imdbRatings[i] > 5): 
		  	new = corr_movie[corr_movie['numRatings'] > 50].sort_values(by='correlation', ascending=False).head(num + 1)
		else: 
			new = corr_movie[corr_movie['numRatings'] > 50].sort_values(by='correlation', ascending=True).head(num + 1)

		for index,row in new.iterrows():
			if int(row['imdbId']) in output.keys(): 
				output[int(row['imdbId'])] += imdbRatings[i]*abs(row['correlation'])
			else:
				output[int(row['imdbId'])] = imdbRatings[i]*abs(row['correlation'])

	recs = sorted(output, key=output.get, reverse=True)[:num+1]
	file = open("recommendations.txt","w")
	for j in range(1,len(recs)): 
		file.write(str(recs[j]))
		file.write("\n") 
	file.close()
	return sorted(output, key=output.get, reverse=True)[:num]

recommend([114709],[10], 5)

