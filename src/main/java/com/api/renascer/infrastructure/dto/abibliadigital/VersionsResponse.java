package com.api.renascer.infrastructure.dto.abibliadigital;

import com.api.renascer.infrastructure.dto.abibliadigital.generic.Version;

import java.util.List;

public record VersionsResponse(List<Version> versions) {
}
