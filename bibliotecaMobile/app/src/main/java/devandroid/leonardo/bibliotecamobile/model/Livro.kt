package devandroid.leonardo.bibliotecamobile.model

import android.os.Parcel
import android.os.Parcelable

data class Livro(
    private var _titulo: String,
    private var _autor: String
) : Parcelable {
    var titulo: String
        get() = _titulo
        set(valor) {
            _titulo = valor
        }

    var autor: String
        get() = _autor
        set(valor) {
            _autor = valor
        }

    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(_titulo)
        parcel.writeString(_autor)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Livro> {
        override fun createFromParcel(parcel: Parcel): Livro {
            return Livro(parcel)
        }

        override fun newArray(size: Int): Array<Livro?> {
            return arrayOfNulls(size)
        }
    }
}
