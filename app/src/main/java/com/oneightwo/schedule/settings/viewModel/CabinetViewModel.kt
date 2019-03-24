package com.oneightwo.schedule.settings.viewModel

import com.oneightwo.schedule.database.base.BaseDao
import com.oneightwo.schedule.database.cabinet.Cabinet
import com.oneightwo.schedule.settings.base.BaseViewModel
import com.oneightwo.schedule.tools.App
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CabinetViewModel : BaseViewModel<Cabinet>() {


//    override val dao: BaseDao<Cabinet> = App.db.cabinetDao()
    override val data = App.db.cabinetDao().getAllData()

    override fun getDao(): BaseDao<Cabinet> {
        return App.db.cabinetDao()
    }

    override fun add(data: Cabinet) {
        val o = Observable.fromCallable {
            getDao().insert(data)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun delete(data: Cabinet) {
        val o = Observable.fromCallable {
            getDao().delete(data)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    override fun update(data: Cabinet) {
        val o = Observable.fromCallable {
            getDao().update(data)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }
}

