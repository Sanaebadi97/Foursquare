package com.sanaebadi.foursquare.presentaion.ui.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import com.sanaebadi.domain.interactors.ExploreVenuesUseCase
import com.sanaebadi.domain.interactors.LoadMoreVenuesUseCase
import com.sanaebadi.domain.interactors.VenuesUseCase
import com.sanaebadi.domain.interactors.base.invoke
import com.sanaebadi.domain.model.ExploreVenuesRequestObject
import com.sanaebadi.domain.model.VenueObject
import com.sanaebadi.foursquare.presentaion.ui.adapter.base.VenueAction
import com.sanaebadi.foursquare.presentaion.ui.adapter.base.BaseAction
import com.sanaebadi.foursquare.presentaion.ui.adapter.base.LoadMoreState
import com.sanaebadi.foursquare.presentaion.ui.viewModel.base.BaseViewModel
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class VenuesViewModel @Inject constructor(
    private val exploreVenuesUseCase: ExploreVenuesUseCase,
    private val loadMoreVenuesUseCase: LoadMoreVenuesUseCase,
    private val venuesUseCase: VenuesUseCase
) : BaseViewModel() {

    private var location: ExploreVenuesRequestObject? = null

    val clickObservable = MutableLiveData<BaseAction>()

    val venues: LiveData<List<VenueObject>> =
        LiveDataReactiveStreams.fromPublisher(venuesUseCase.invoke())


    fun exploreVenues(lat: String, long: String) {
        location = ExploreVenuesRequestObject(lat, long)
        exploreVenuesUseCase(ExploreVenuesRequestObject(lat, long))
            .onError()
            .subscribe()
            .track()
    }

    fun loadMoreObserver(loadMoreObservable: PublishSubject<LoadMoreState>) {
        loadMoreObservable.subscribe { shouldLoad ->
            if (shouldLoad == LoadMoreState.LOAD) {
                loadMoreVenuesUseCase.invoke(location)
                    .onError()
                    .subscribe({
                        loadMoreObservable.onNext(LoadMoreState.NOT_LOAD)
                    }, {
                        loadMoreObservable.onNext(LoadMoreState.NOT_LOAD)
                    }).track()
            }

        }.track()
    }

    fun observeClicks(action: Observable<BaseAction>) {
        action.subscribe {
            when (it) {

                is VenueAction -> {
                    clickObservable.value = it
                }
                else -> {
                }

            }
        }.track()

    }
}