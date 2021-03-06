package com.workspace.githubusertwo.view


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.workspace.githubusertwo.api.ApiClient
import com.workspace.githubusertwo.model.SearchResponse
import com.workspace.githubusertwo.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    private val list: MutableLiveData<List<UserModel>> = MutableLiveData()
    val listResult: LiveData<List<UserModel>> = list


    fun onResponse(query: String) {
        val call = ApiClient.service.getSearchResult(query)
        call.enqueue(object : Callback<SearchResponse> {

            override fun onFailure(call: Call<SearchResponse>?, t: Throwable?) {
                list.postValue(emptyList())

            }

            override fun onResponse(
                call: Call<SearchResponse>?, response: Response<SearchResponse>
            ) {
                if (response.isSuccessful) {
                    val data: List<UserModel> = response.body()?.items!!
                    list.postValue(data)
                        if (data.isEmpty()){
                            list.postValue(data)
                        }
                }

            }

        })
    }

}


