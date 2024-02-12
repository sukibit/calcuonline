package com.oliversolutions.dev.calcuonline.framework.datasources.cloud.firebase

import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.oliversolutions.dev.calcuonline.data.models.CalculatorData
import com.oliversolutions.dev.calcuonline.data.datasources.cloud.CalculatorFirebaseDataSource
import com.oliversolutions.dev.calcuonline.data.models.CategoryData
import com.oliversolutions.dev.calcuonline.utils.orFalse
import com.oliversolutions.dev.calcuonline.utils.orZero
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class CalculatorFirebaseDataSourceImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore,
    crashlytics: FirebaseCrashlytics,
) : CalculatorFirebaseDataSource, BaseFirebaseDataSource(crashlytics) {

    companion object {
        const val DOCUMENT_CALCULATORS = "calculators"
        const val DOCUMENT_CATEGORIES = "categories"
        const val CATEGORY_ID_STRING = "category_id"
    }

    override suspend fun getCategories(): List<CategoryData> {
        return executeFirebaseOperation {
            val result = firebaseFirestore.collection(DOCUMENT_CATEGORIES).get().await()
            val firebaseCategoryList = mutableListOf<CategoryData>()
            for (document in result) {
                firebaseCategoryList.add(
                    CategoryData(
                        id = (document.data["id"] as? Long).orZero(),
                        iconUrl = (document.data["icon_url"] as? String).orEmpty(),
                        resId = (document.data["res_id"] as? String).orEmpty(),
                    )
                )
            }
            firebaseCategoryList
        }
    }

    override suspend fun getCalculators(): List<CalculatorData> {
        return executeFirebaseOperation {
            val db = Firebase.firestore
            val result = db.collection(DOCUMENT_CALCULATORS).get().await()
            val firebaseCalculatorList = mutableListOf<CalculatorData>()
            for (document in result) {
                val documentDataCalculator = document.data
                val categoryDoc = document.getDocumentReference(CATEGORY_ID_STRING)?.get()?.await()
                if (categoryDoc != null && categoryDoc.exists()) {
                    val categoryId = categoryDoc.getLong("id").orZero()
                    firebaseCalculatorList.add(
                        CalculatorData(
                            resId = (documentDataCalculator["res_id"] as? String).orEmpty(),
                            isFavorite = (documentDataCalculator["is_favorite"] as? Boolean).orFalse(),
                            isFeatured = (documentDataCalculator["is_featured"] as? Boolean).orFalse(),
                            iconUrl = (documentDataCalculator["icon_url"] as? String).orEmpty(),
                            categoryId = categoryId,
                        )
                    )
                }
            }
            firebaseCalculatorList
        }
    }
}