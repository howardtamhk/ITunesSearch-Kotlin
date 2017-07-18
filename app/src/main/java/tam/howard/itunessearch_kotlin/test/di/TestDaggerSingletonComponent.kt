package tam.howard.itunessearch_kotlin.test.di

import dagger.Component
import tam.howard.itunessearch_kotlin.api.di.ApiComponent
import tam.howard.itunessearch_kotlin.di.ActivityScopes
import tam.howard.itunessearch_kotlin.test.TestDaggerSingletonActivity

/**
 * Created by Howard on 18/7/2017.
 */
@ActivityScopes
@Component(dependencies = arrayOf(ApiComponent::class), modules = arrayOf(TestDaggerSingletonModule::class))
interface TestDaggerSingletonComponent {

    fun inject(activity: TestDaggerSingletonActivity)
}