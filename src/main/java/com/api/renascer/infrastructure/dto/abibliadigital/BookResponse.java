package com.api.renascer.infrastructure.dto.abibliadigital;

import com.api.renascer.infrastructure.dto.abibliadigital.generic.Abbrev;

public record BookResponse(Abbrev abbrev, String author, int chapters, String group, String name, String testament) {
}
