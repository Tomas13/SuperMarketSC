package kazpost.kz.supermarketsc.ui.scan;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.baozi.Zxing.CaptureActivity;
import com.baozi.Zxing.ZXingConstants;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.tbruyelle.rxpermissions.RxPermissions;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import kazpost.kz.supermarketsc.App;
import kazpost.kz.supermarketsc.CommonUtils;
import kazpost.kz.supermarketsc.R;
import kazpost.kz.supermarketsc.di.component.DaggerActivityComponent;
import kazpost.kz.supermarketsc.ui.chooseindex.ChooseIndexActivity;

import static kazpost.kz.supermarketsc.CommonUtils.isBarcode;
import static kazpost.kz.supermarketsc.CommonUtils.isRow;

public class ScanActivity extends AppCompatActivity {

    private static final String TAG = ScanActivity.class.getSimpleName();

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

        DaggerActivityComponent.builder()
                .applicationComponent(((App) getApplication()).getComponent())
                .build().inject(this);

        mViewModel = ViewModelProviders.of(this, factory).get(ScanViewModel.class);


        mViewModel.getmData().observe(this, s -> {
            if (s != null) {
//                hideLoading();
                Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            } else {
//                showLoading();
            }
        });

        mViewModel.getRow().observe(this, s -> Log.d(TAG, "row: " + s));
        mViewModel.getCell().observe(this, s -> Log.d(TAG, "cell: " + s));
        mViewModel.getBarcode().observe(this, s -> Log.d(TAG, "barcode: " + s));

        RxTextView.textChanges(etPostcode)
                .skipInitialValue()
                .filter(charSequence -> isBarcode(charSequence.toString()))
                .subscribe(charSequence -> mViewModel.setBarcode(charSequence.toString()));

        RxTextView.textChanges(etRow)
                .skipInitialValue()
                .filter(charSequence -> isRow(charSequence.toString()))
                .subscribe(charSequence -> mViewModel.setRow(charSequence.toString()));


        mViewModel.getState().observe(this, this::handleState);


    }

    private void handleState(Status state) {
        if (state == Status.ERROR) {
            hideLoading();
            Toast.makeText(this, "hideloading", Toast.LENGTH_SHORT).show();
        }

        if (state == Status.LOADING) {
            showLoading();
            Toast.makeText(this, "loading", Toast.LENGTH_SHORT).show();
        }


    }

    public void showLoading() {
        hideLoading();
        mProgressDialog = CommonUtils.showLoadingDialog(this);
    }

    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
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

    @OnClick({R.id.btn_send, R.id.btn_clean, R.id.btn_scan})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_send:
                mViewModel.netwo();
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
                                // I can control the camera now
//                        Toast.makeText(this, "Permission GRANTED", Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(this, CaptureActivity.class);
                                intent.putExtra(ZXingConstants.ScanIsShowHistory, true);
                                startActivityForResult(intent, ZXingConstants.ScanRequestCode);
                            } else {
                                // Oups permission denied
                                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
                            }
                        });
                break;
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
}
