/*
 * Copyright 2021-2024 Ona Systems, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.smartregister.fhircore.quest

import com.google.android.fhir.FhirEngineConfiguration
import com.google.android.fhir.FhirEngineProvider
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/** A [TestRule] that cleans up [FhirEngineProvider] instance after each test run. */
class FhirEngineProviderTestRule : TestRule {
  override fun apply(base: Statement, p1: Description): Statement {
    return object : Statement() {
      override fun evaluate() {
        try {
          FhirEngineProvider.init(FhirEngineConfiguration(testMode = true))
          base.evaluate()
        } catch (exception: IllegalStateException) { // Necessary to avoid crashing tests
          println(exception)
        } finally {
          try {
            FhirEngineProvider.cleanup()
          } catch (
            e: IllegalStateException,) { // TODO investigate why testMode is false at this point
            println(e)
          }
        }
      }
    }
  }
}
