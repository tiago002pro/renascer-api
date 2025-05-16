package com.api.renascer.application.web.abibliadigital;

import com.api.renascer.application.web.abibliadigital.generic.Abbrev;

public record BookResponse(Abbrev abbrev, String author, int chapters, String group, String name, String testament) {
}
