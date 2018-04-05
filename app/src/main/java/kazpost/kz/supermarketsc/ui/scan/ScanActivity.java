package kazpost.kz.supermarketsc.ui.scan;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.baozi.Zxing.CaptureActivity;
import com.baozi.Zxing.ZXingConstants;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kazpost.kz.supermarketsc.App;
import kazpost.kz.supermarketsc.R;
import kazpost.kz.supermarketsc.ui.chooseindex.ChooseIndexActivity;
import kazpost.kz.supermarketsc.utils.ToastMessage;

import static kazpost.kz.supermarketsc.utils.CommonUtils.isBarcode;
import static kazpost.kz.supermarketsc.utils.CommonUtils.isRow;

public class ScanActivity extends AppCompatActivity {

    //    private static final String TAG = ScanActivity.class.getSimpleName();
    private static final String TAG = "ScanActProgress";

    ScanViewModel mViewModel;
    @BindView(R.id.et_postcode)
    EditText etPostcode;
    @BindView(R.id.et_row)
    EditText etRow;

    private ProgressDialog mProgressDialog;

    @Inject
    ScanViewModelFactory factory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        App.getApp().getmDiComponent().inject(this);

        mViewModel = ViewModelProviders.of(this, factory).get(ScanViewModel.class);

        initProgress();

        initTextViewListeners();

        setupToastMessages();

        IfIndexIsNotSetStartChooseActivity();
    }

    private void IfIndexIsNotSetStartChooseActivity() {
        if (!mViewModel.isMarketIndexExist()) {
            startActivity(new Intent(this, ChooseIndexActivity.class));
        }
    }

    private void setupToastMessages() {
        mViewModel.getmToast().observe(this, (ToastMessage.ToastObserver) message -> {
            showToast(message);
        });
    }

    private void initProgress() {
        mViewModel.getProgressState().observe(this, aBoolean -> {
            if (aBoolean) {
                Log.d(TAG, "getProgress: " + aBoolean);
                showLoading("Загрузка");
            } else {
                Log.d(TAG, "getProgress: " + aBoolean);
                hideLoading();
            }
        });
    }

    private void initTextViewListeners() {


      /*  RxTextView.textChanges(etPostcode)
                .skipInitialValue()
                .filter(charSequence -> isBarcode(charSequence.toString()))
                .subscribe(charSequence -> mViewModel.setBarcode(charSequence.toString()));

        RxTextView.textChanges(etRow)
                .skipInitialValue()
                .filter(charSequence -> isRow(charSequence.toString()))
                .subscribe(charSequence -> mViewModel.setRow(charSequence.toString()));*/
    }

    @OnClick({R.id.btn_send, R.id.btn_clean, R.id.btn_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                if (isBarcode(etPostcode.getText().toString()) && isRow(etRow.getText().toString())) {
                    mViewModel.netwo(etPostcode.getText().toString(), etRow.getText().toString());
                } else {
                    showToast("Неверно введен баркод или номер ячейки");
                }
                break;
            case R.id.btn_clean:
                break;
            case R.id.btn_scan:
                RxPermissions rxPermissions = new RxPermissions(this); // where this is an Activity instance
                // Must be done during an initialization phase like onCreate
                rxPermissions
                        .request(Manifest.permission.CAMERA)
                        .subscribe(granted -> {
                            if (granted) { // Always true pre-M
                                Intent intent = new Intent(this, CaptureActivity.class);
                                intent.putExtra(ZXingConstants.ScanIsShowHistory, true);
                                startActivityForResult(intent, ZXingConstants.ScanRequestCode);
                            } else {
                                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
        }
    }

    private void showToast(String msg) {
        Toast toast = Toast.makeText(this, msg, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.TOP, 0, 50);

        //trying to increase the toast font
        ViewGroup group = (ViewGroup) toast.getView();
        TextView messageTextView = (TextView) group.getChildAt(0);
        messageTextView.setTextSize(20);

        toast.setText(msg);
        toast.show();
    }

    public void showLoading(String... message) {
        hideLoading();

        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            Log.d(TAG, "showLoading: created mProgress " + mProgressDialog);
        }
        mProgressDialog.show();

        if (mProgressDialog.getWindow() != null) {
            mProgressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        mProgressDialog.setContentView(R.layout.progress_dialog);
        mProgressDialog.setIndeterminate(false);

        View view = LayoutInflater.from(this).inflate(R.layout.progress_dialog, null);
        mProgressDialog.setContentView(view);

        TextView textView = view.findViewById(R.id.tv_progress);
        if (message.length > 0) textView.setText(message[0]);


        mProgressDialog.setCancelable(true);
        mProgressDialog.setCanceledOnTouchOutside(false);


    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        switch (requestCode) {
            case ZXingConstants.ScanRequestCode:
                if (resultCode == ZXingConstants.ScanRequestCode) {
                    String result = data.getStringExtra(ZXingConstants.ScanResult);

                    if (etPostcode.hasFocus()) {
                        etPostcode.setText(result);
                        etRow.requestFocus();
                        break;
                    }

                    if (etRow.hasFocus()) {
                        etRow.setText(result);
                        etPostcode.requestFocus();
                        break;
                    }

//                    setStrings(result);
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.scan_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_item) {
            Intent intent = new Intent(this, ChooseIndexActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoading();
        mProgressDialog = null;

        Log.d(TAG, "onDestroy: mProgressDialog =  " + mProgressDialog);
    }
}
