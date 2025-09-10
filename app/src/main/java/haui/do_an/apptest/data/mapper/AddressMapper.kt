package haui.do_an.apptest.data.mapper

import haui.do_an.apptest.data.models.Address

fun Address.toDomainModel(): haui.do_an.apptest.domain.models.Address{
    return haui.do_an.apptest.domain.models.Address(
        display_name = display_name,
        lat = lat,
        lon = lon
    )
}