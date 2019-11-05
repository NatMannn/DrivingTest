package buu.informatics59160953.drivingtest


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import buu.informatics59160953.drivingtest.databinding.FragmentTestRankBinding
import com.google.android.material.snackbar.Snackbar

/**
 * A simple [Fragment] subclass.
 */
class TestRankFragment : Fragment() {

    data class Question(
        val text: String,
        val answers: List<String>)

    // The first answer is the correct one.  We randomize the answers before showing the text.
    // All questions must have four answers.  We'd want these to contain references to string
    // resources so we could internationalize. (Or better yet, don't define the questions in code...)
    private val questions: MutableList<Question> = mutableListOf(
        Question(
            text = "รถต่อไปนี้ได้รับการยกเว้นไม่ต้องจดทะเบียน?",
            answers = listOf(
                "รถที่เจ้าของรถแจ้งการไม่ใช้รถ",
                "รถยนต์นำเข้า",
                "รถสามล้อส่วนบุคคล",
                "รถจักรยานยนต์นำมาใช้ในหมู่บ้าน"
            )
        ),
        Question(
            text = "ข้อใดไม่ใช่การตรวจสอบน้ำมัน?",
            answers = listOf("ดมกลิ่น", "สี", "ปริมาณ", "ความหนืด สิ่งเจือปน")
        ),
        Question(
            text = "แบตเตอรี่รถยนต์จะมีขนาดแรงดันไฟฟ้ากี่โวลท์?",
            answers = listOf("12 โวลท์", "15 โวลท์", "24 โวลท์", "220 โวลท์")
        ),
        Question(
            text = "หากประสงค์จะย้ายรถ เจ้าของรถต้องแจ้งย้ายรถต่อนายทะเบียนภายในกี่วัน?",
            answers = listOf("15 วัน", "30 วัน", "7 วัน", "60 วัน")
        ),
        Question(
            text = "น้ำที่ใช้เติมแบตเตอรี่ ควรใช้น้ำชนิดใด?",
            answers = listOf("น้ำกลั่น", "น้ำฝน", "น้ำบาดาล", "น้ำสบู่")
        ),
        Question(
            text = "การถอดขั้วแบเตอรี่ ควรถอดขั้วใดก่อน?",
            answers = listOf("ขั้วลบ", "ขั้นบวก", "ขั้วใดก่อนก็ได้", "ถอดขั้วบวกและขั้วลบพร้อมกัน")
        ),
        Question(
            text = "ถ้าเติมลมยางอ่อนเกกินไป จะมีผลอย่างไรกับยาง?",
            answers = listOf(
                "ทำให้ดอกยางทางด้านข้างทั้งสองสึกหรอ",
                "ทำให้กินน้ำมันน้อยลง",
                "การขับขี่จะแข็งกระด้าง",
                "ดอกยางตรงกลางจะสึกเร็วกว่าปกติ"
            )
        ),
        Question(
            text = "สภาพท่อยางหม้อน้ำที่ยังใช้งานได้ดีต้องมีลักษณะอย่างไร?",
            answers = listOf(
                "บีบแล้วต้องมีความยืดหยุ่น",
                "บีบแล้วต้องแข็งกระด้าง",
                "มีรอยบวมที่ท่อยาง",
                "มีรอยฉีกขาด"
            )
        ),
        Question(
            text = "รถที่ไม่เสียภาษีประจำปีภายในกำหนดจะต้องเสียเงินเพิ่มเท่าใด?",
            answers = listOf(
                "ร้อยละ 1 ต่อเดือน",
                "ร้อยละ 1 ต่อปี",
                "ร้อยละ 10 ต่อเดือน",
                "ร้อยละ 20 ต่อปี"
            )
        ),
        Question(
            text = "รถจักรยานยนต์ที่มีอายุครบปีต้องนำไปตรวจสภาพรถก่อนเสียภาษีประจำปี?",
            answers = listOf("5 ปี", "1 ปี", "3 ปี", "2 ปี")
        )
    )

    lateinit var currentQuestion: Question
    lateinit var answers: MutableList<String>
    private var questionIndex = 0
    private var scoreTest = 0
    private val numQuestions = Math.min((questions.size + 1) / 2, 5)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = DataBindingUtil.inflate<FragmentTestRankBinding>(inflater,
            R.layout.fragment_test_rank,container,false)
        // Inflate the layout for this fragment


        randomizeQuestions()

        // Bind this fragment class to the layout
        binding.game = this

        // Set the onClickListener for the submitButton
        binding.submitTestButton.setOnClickListener @Suppress("UNUSED_ANONYMOUS_PARAMETER")
        { view: View ->
            val checkedId = binding.questionRadioGroup.checkedRadioButtonId
            // Do nothing if nothing is checked (id == -1)
            if (-1 != checkedId) {
                var answerIndex = 0
                when (checkedId) {
                    R.id.secondAnsRadioButton -> answerIndex = 1
                    R.id.thirdAnsRadioButton -> answerIndex = 2
                    R.id.fourthAnsRadioButton -> answerIndex = 3
                }
                // The first answer in the original question is always the correct one, so if our
                // answer matches, we have the correct answer.
                if (answers[answerIndex] == currentQuestion.answers[0]) {
                    questionIndex++

                    scoreTest++

                    // Advance to the next question
                    if (questionIndex < numQuestions) {
                        currentQuestion = questions[questionIndex]
                        setQuestion()
                        binding.invalidateAll()
                    } else {
                        // We've won!  Navigate to the gameWonFragment.
                        view.findNavController()
                            .navigate(
                                TestRankFragmentDirections.actionTestRankToResult(
                                    scoreTest
                                )
                            )
                    }
                } else {
                    // Game over! A wrong answer sends us to the gameOverFragment.
                    view.findNavController()
                        .navigate(
                            TestRankFragmentDirections.actionTestRankToResult(
                                scoreTest
                            )
                        )
                }
            }
        }


//        binding.submitTestButton.setOnClickListener{ view ->
//            view.findNavController().navigate(R.id.action_testRank_to_result)
//        }


        return binding.root
    }

    private fun randomizeQuestions() {
        questions.shuffle()
        questionIndex = 0
        setQuestion()
    }

    // Sets the question and randomizes the answers.  This only changes the data, not the UI.
    // Calling invalidateAll on the FragmentGameBinding updates the data.
    private fun setQuestion() {
        currentQuestion = questions[questionIndex]
        // randomize the answers into a copy of the array
        answers = currentQuestion.answers.toMutableList()
        // and shuffle them
        answers.shuffle()
      (activity as AppCompatActivity).supportActionBar?.title = getString(R.string.title_android_question, questionIndex + 1, numQuestions)
    }




    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var snackbar = view?.let { Snackbar.make(it,"เริ่มแบบทดสอบ",Snackbar.LENGTH_LONG) }
        snackbar?.show()
    }




}
