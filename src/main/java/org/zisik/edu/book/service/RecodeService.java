package org.zisik.edu.book.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.zisik.edu.book.repositroy.RecodeRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecodeService {
    private final RecodeRepository recodeRepository;
}
