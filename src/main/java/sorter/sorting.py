import pandas as pd
import numpy as np
import sys

first_arg = sys.argv[1]

def sorting(genre):

  df = pd.read_csv('src/main/java/recommend/ratings.csv', usecols=['userId','movieId','rating'])

  movie_titles = pd.read_csv('src/main/java/recommend/movies.csv', usecols=['movieId','title','genres'])
  movie_titles = movie_titles[movie_titles['genres'].str.contains(genre)]

  imdb_to_id = pd.read_csv('src/main/java/recommend/links.csv', usecols=['movieId','imdbId'])

  ratings = pd.DataFrame(df.groupby('movieId')['rating'].mean())
  ratings['numRatings'] = pd.DataFrame(df.groupby('movieId')['rating'].count())

  movie_titles = pd.merge(movie_titles,imdb_to_id, left_on = 'movieId', right_on = 'movieId')

  movie_titles = movie_titles.join(ratings['numRatings'], on = 'movieId')
  movie_titles = movie_titles.join(ratings['rating'], on = 'movieId')
  movie_titles['sorter'] = movie_titles['rating'] * movie_titles['numRatings']

  new = movie_titles[movie_titles['numRatings'] > 25].sort_values(by='sorter', ascending=False).head(15)

  for index,row in new.iterrows():
    print(str(row['imdbId']))
  return
print(sorting(first_arg))
