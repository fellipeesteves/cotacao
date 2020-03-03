/*
 * Copyright 2019 Ryan McGuinness
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rationaldevelopers.examples.concurrent;


import com.rationaldevelopers.examples.model.Cotacao;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.WeakHashMap;

public class ManagedThreadLocal {
    private static ThreadLocal<Map<String, Object>> CONTEXT = ThreadLocal.withInitial(WeakHashMap::new);
    private static final String KEY_ID = "___ID_@RD__";
    private static final String KEY_COTACAO = "___@RD_Cotacao__";

    public ManagedThreadLocal(final List<Cotacao> optional) {
        setCurrentCotacao(optional);
    }

    public static String getTraceId() {
        if (ManagedThreadLocal.CONTEXT.get().get(KEY_ID) == null) {
            ManagedThreadLocal.CONTEXT.get().put(KEY_ID, UUID.randomUUID().toString());
        }
        return (String) ManagedThreadLocal.CONTEXT.get().get(KEY_ID);
    }

    public static Optional<Cotacao> getCurrentCotacao() {
        return Optional.ofNullable((Cotacao)ManagedThreadLocal.CONTEXT.get().get(KEY_COTACAO));
    }

    public static void setCurrentCotacao(final List<Cotacao> existing) {
        //existing.ifPresent(p -> ManagedThreadLocal.CONTEXT.get().put(KEY_COTACAO, p));
    }

    public static void clear() {
        ManagedThreadLocal.CONTEXT.get().clear();
        ManagedThreadLocal.CONTEXT.remove();
    }
}