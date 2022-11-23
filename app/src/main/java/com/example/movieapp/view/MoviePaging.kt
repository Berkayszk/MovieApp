package com.example.movieapp.view

import androidx.compose.ui.unit.Constraints
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieapp.model.MovieResult
import com.example.movieapp.remote.MovieInterface
import com.example.movieapp.roomdb.Movie
import com.example.movieapp.util.Util.API_KEY

class MoviePaging(val s : String,val movieInterface: MovieInterface) : PagingSource<Int,MovieResult>() {
    override fun getRefreshKey(state: PagingState<Int, MovieResult>): Int? {
       return state.anchorPosition?.let {
            val anchorPage = state?.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1)?:anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieResult> {
        val page= params.key?:1
        return try {
            val data = movieInterface.getAllMovies(s,page,API_KEY)

            LoadResult.Page(
                data = data.body()?.Search!!,
                prevKey = if(page==1) null else page-1,
                nextKey = if (data.body()?.Search?.isEmpty()!!) null else page+1
            )


        }catch (e:Exception){
            LoadResult.Error(e)
        }
    }
}