package com.ozontech.homework2.data.repositories

import com.ozontech.homework2.data.dto.Product
import com.ozontech.homework2.data.dto.ProductInList

class MockRepository {

    val productInListDTOs = mutableListOf(
        ProductInList(
            guid = "b5f5852b-3b8c-4857-9f53-ac5c2b6a3b2f",
            image = "https://cdn1.ozone.ru/s3/multimedia-j/6131838211.jpg",
            name = "Борщ по-домашнему",
            price = "199",
            rating = 4.6,
            isFavorite = false,
            isInCart = false
        ),
        ProductInList(
            guid = "5cbbb7d0-e9c6-4932-8d85-79313c58e465",
            image = "https://cdn1.ozone.ru/s3/multimedia-u/6255350190.jpg",
            name = "Ваш любимый Сальчичон",
            price = "79",
            rating = 5.0,
            isFavorite = true,
            isInCart = false
        ),
        ProductInList(
            guid = "cc87f70d-7570-42ee-a221-db8887534896",
            image = "https://cdn1.ozone.ru/s3/multimedia-9/6012020949.jpg",
            name = "Бельгийская смесь \"Мираторг\"",
            price = "149",
            rating = 3.2,
            isFavorite = false,
            isInCart = false
        ),
        ProductInList(
            guid = "627de4ca-b4fd-46ea-9201-562448af6fc1",
            image = "https://cdn1.ozone.ru/s3/multimedia-f/6199943655.jpg",
            name = "Джем Ozon Express",
            rating = 4.6,
            price = "1390",
            isFavorite = false,
            isInCart = false
        ),
        ProductInList(
            guid = "493ef2ba-cd2f-4ca5-b3af-f9091115700e",
            image = "https://cdn1.ozone.ru/s3/multimedia-z/6267606395.jpg",
            name = "Мармелад клюква с ягелем",
            rating = 2.8,
            price = "319",
            isFavorite = true,
            isInCart = false
        ),
        ProductInList(
            guid = "ceadee7a-9d10-4987-8ed3-0624d6fbe5c0",
            image = "https://cdn1.ozone.ru/s3/multimedia-8/6288053576.jpg",
            name = "Авокадо Хасс",
            price = "600",
            rating = 4.5,
            isFavorite = false,
            isInCart = false
        ),
        ProductInList(
            guid = "c3cfe1a8-6eec-4e9f-a260-490e128763f4",
            image = "https://cdn1.ozone.ru/s3/multimedia-4/6099200308.jpg",
            name = "Молоко",
            price = "65",
            rating = 5.0,
            isFavorite = true,
            isInCart = false
        ),
        ProductInList(
            guid = "a9cd4415-48b0-4557-8f29-6d28824fe91b",
            image = "https://cdn1.ozone.ru/s3/multimedia-z/6096233435.jpg",
            name = "Мороженое сорбет",
            price = "110",
            rating = 4.0,
            isFavorite = false,
            isInCart = false
        ),
        ProductInList(
            guid = "856c1c90-1b8e-46ba-a5de-bc818dc1bdbd",
            image = "https://cdn1.ozone.ru/s3/multimedia-d/6206219785.jpg",
            name = "Сырок творожный",
            price = "23",
            rating = 5.0,
            isFavorite = true,
            isInCart = false
        ),
        ProductInList(
            guid = "ebe799f5-6e01-45dc-8139-e714753896c1",
            image = "https://cdn1.ozone.ru/s3/multimedia-j/6167822191.jpg",
            name = "Блинчики домашние",
            price = "189",
            rating = 3.0,
            isFavorite = false,
            isInCart = false
        ),
    )
    val productDTO = mutableListOf(
        Product(
            guid = "b5f5852b-3b8c-4857-9f53-ac5c2b6a3b2f",
            availableCount = 10,
            count = 2,
            description = "Этот борщ сведёт вас с ума!",
            images = listOf("https://cdn1.ozone.ru/s3/multimedia-j/6131838211.jpg"),
            name = "Борщ по-домашнему",
            price = "199",
            rating = 4.6,
            weight = 0.4
        ),
        Product(
            guid = "5cbbb7d0-e9c6-4932-8d85-79313c58e465",
            additionalParams = mapOf("Качество" to "10/10"),
            availableCount = 0,
            count = 0,
            description = "Это очень вкусная колбаска",
            images = listOf("https://cdn1.ozone.ru/s3/multimedia-u/6255350190.jpg"),
            isFavorite = true,
            name = "Ваш любимый Сальчичон",
            price = "79",
            rating = 5.0,
            weight = 0.2
        ),
        Product(
            guid = "cc87f70d-7570-42ee-a221-db8887534896",
            availableCount = 1000,
            count = 1,
            description = "Замороженное счастье. Достаточно кинуть на сковородку, потушить 5-10 минут и готово",
            images = listOf("https://cdn1.ozone.ru/s3/multimedia-9/6012020949.jpg"),
            name = "Бельгийская смесь \"Мираторг\"",
            price = "149",
            rating = 3.2
        ),
        Product(
            guid = "627de4ca-b4fd-46ea-9201-562448af6fc1",
            availableCount = 10000000,
            count = 654,
            description = "Наш джем собственного приготовления. Наслаждайтесь!",
            images = listOf("https://cdn1.ozone.ru/s3/multimedia-f/6199943655.jpg"),
            name = "Джем Ozon Express",
            price = "1390",
            rating = 4.6,
            weight = 0.15
        ),
        Product(
            guid = "493ef2ba-cd2f-4ca5-b3af-f9091115700e",
            availableCount = 45,
            count = 12,
            description = "Новая линейка наивкуснейших мармеладов. Самые невероятные вкусы на планете",
            images = listOf("https://cdn1.ozone.ru/s3/multimedia-z/6267606395.jpg"),
            isFavorite = true,
            name = "Мармелад клюква с ягелем",
            price = "319",
            rating = 2.8,
            weight = 1.2
        ),
        Product(
            guid = "ceadee7a-9d10-4987-8ed3-0624d6fbe5c0",
            name = "Авокадо Хасс",
            "600",
            "Солнечное авокадо из долины с ручьём",
            4.5,
            images = listOf("https://cdn1.ozone.ru/s3/multimedia-8/6288053576.jpg"),
            availableCount = 689,
            count = 23
        ),
        Product(
            "c3cfe1a8-6eec-4e9f-a260-490e128763f4",
            "Молоко",
            "65",
            "Наше молоко вкуснее и дешевле любых аналогов на рынке",
            5.0,
            true,
            images = listOf("https://cdn1.ozone.ru/s3/multimedia-4/6099200308.jpg"),
            weight = 1.0,
            count = 23,
            availableCount = 90,
            additionalParams = mapOf(
                "Высота" to "90", "Ширина" to "10", "Глубина" to "10"
            )
        ),
        Product(
            "a9cd4415-48b0-4557-8f29-6d28824fe91b",
            "Мороженое сорбет",
            "110",
            "Сорбет, это вам не молочное мороженое, от него не бывает аллергии",
            4.0,
            images = listOf("https://cdn1.ozone.ru/s3/multimedia-z/6096233435.jpg"),
            weight = 0.23,
            count = 3,
            availableCount = 874,
        ),
        Product(
            "856c1c90-1b8e-46ba-a5de-bc818dc1bdbd",
            "Сырок творожный",
            "23",
            "Обожаю эти сырки, они просто тают во рту, растекаясь по нёбу",
            5.0,
            true,
            images = listOf("https://cdn1.ozone.ru/s3/multimedia-d/6206219785.jpg"),
            weight = 0.05,
            count = 99999,
            availableCount = 67456,
            additionalParams = mapOf(
                "Вкус" to "100",
                "Размер" to "Слишком маленький",
                "Минусы" to "нет"
            )
        ),
        Product(
            "ebe799f5-6e01-45dc-8139-e714753896c1",
            "Блинчики домашние",
            "189",
            "Блинчики очень даже ничего. Ароматные, мягкие. Абсолютно достойны вашего внимания",
            3.0,
            images = listOf("https://cdn1.ozone.ru/s3/multimedia-j/6167822191.jpg"),
            availableCount = 9534,
            count = 39167,
            weight = 0.22,
            additionalParams = mapOf(
                "Температура" to "50'c",
                "Количество в упаковке" to "хотелось бы и побольше",
                "Начинка" to "нет"
            )
        )
    )
}