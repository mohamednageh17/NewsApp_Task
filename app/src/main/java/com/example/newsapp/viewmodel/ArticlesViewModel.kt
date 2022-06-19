package com.example.newsapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.domain.model.ArticleModel
import com.example.domain.use_cases.*
import com.example.movieapp.handle_state.StateLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


@HiltViewModel
class ArticlesViewModel @Inject constructor(
    private val fetchNewsFromApiUseCase: FetchNewsFromApiUseCase,
    private val addToFavouriteUseCase: AddToFavouriteUseCase,
    private val removeArticleFromFavouritesUseCase: RemoveArticleFromFavouritesUseCase,
    private val getFavouriteArticlesFromLocalUseCase: GetFavouriteArticlesFromLocalUseCase,
    private val checkIfArticleIsFavouriteUseCase: CheckIfArticleIsFavouriteUseCase
) : BaseViewModel() {
    private val _articlesLiveData by lazy { StateLiveData<List<ArticleModel>>() }
    val articlesLiveDate = _articlesLiveData

    private var _isFavourite = MutableLiveData<Boolean>()
    val isFavourite: LiveData<Boolean> get() = _isFavourite

    fun fetchNewsFeed(page: Int) {
        fetchNewsFromApiUseCase(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _articlesLiveData.postLoading() }
            .subscribe({
                _articlesLiveData.postSuccess(it)
            }, {
                _articlesLiveData.postError(it)
            }).addTo(compositeDisposable)
    }

    fun addArticleToFavouriteList(articleModel: ArticleModel) {
        addToFavouriteUseCase(articleModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _isFavourite.postValue(true) }, {})
    }

    fun removeArticleFromFavouriteList(articleModel: ArticleModel) {
        removeArticleFromFavouritesUseCase(articleModel)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ _isFavourite.postValue(false) }, {})
    }

    fun getFavouriteArticlesFromLocalDB() {
        getFavouriteArticlesFromLocalUseCase()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { _articlesLiveData.postLoading() }
            .subscribe({
                _articlesLiveData.postSuccess(it)

            }, {
                _articlesLiveData.postError(it)
            }).addTo(compositeDisposable)
    }

    fun checkIfArticleIsFavourite(id: String) {
        checkIfArticleIsFavouriteUseCase(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it != null) _isFavourite.postValue(true) else _isFavourite.postValue(false)
            },
                { _isFavourite.postValue(false) })
    }
}